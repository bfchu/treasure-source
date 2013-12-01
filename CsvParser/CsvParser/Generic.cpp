// ----------------------------------------------------------------------------
//							Generic.cpp     
// Implementation of various classes.
// 
// 
// Copyright © S9N9S2012  All rights reserved.
//
/////////////////////////////////////////////////////////////////////////////
// 
// Author: Kevin Ablett
// <nl>Created: 5/24/09
// 
/////////////////////////////////////////////////////////////////////////////
//
//	$History: Generic.cpp $
// 
///////////////////////////////////////////////////////////////////////////////

// --- includes ---------------------------------------------------------------

#include <cassert>
#include <io.h>
#include <string>
#include <locale>
#include <iostream>
#include <iomanip>
#include <sstream>
#include <errno.h>

#include "Generic.h"

#if !defined(MAX_PATH)
#include "WinDef.h"		// for MAX_PATH
#endif // MAX_PATH

// --- Data Declarations ------------------------------------------------------

#ifdef _DEBUG
#include "stdafx.h"
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

// --- Function Definitions ---------------------------------------------------

unsigned int GetFileExtension(const std_string &rPath, std_string &rExtension)
{
	assert(rPath[0]);
	unsigned int unDotPos = rPath.rfind('.');
	if(std_string::npos != unDotPos)
	{
		rExtension = rPath.substr(unDotPos);
	}
	return unDotPos;
}

bool DisallowExtension(const std_string &rPath, const std_string &newExtension)
{
	bool retval = true;
	std_string anOldExtension;
	unsigned int unDotPos = GetFileExtension(rPath, anOldExtension);
	
	if(std_string::npos != unDotPos)
	{
		std_string aCopyOfNewExtension = newExtension;
		RaiseCase(aCopyOfNewExtension);
		RaiseCase(anOldExtension);
		if(aCopyOfNewExtension == anOldExtension)
		{
			retval = false;
		}
	}
	return retval;
} // DisallowExtension()

//@doc Tools.cpp
//@rdesc void 
//@parm std_string &rPath	The Path that is to be modified.
//@parm  const std_string &newExtension.  It is assumed that newExtension does not
// begin with a '.'.
//@mfunc This function replaces the existing file extension with the new one.
// If rPath is empty, nothing is done.
// If rPath has no extension, the new one is appended
// If rPath has an extension, the old one is replaced by the new one.
void ReplaceExtension(std_string &rPath, const std_string &newExtension)
{
	// Step through this the first time to make sure it works, then delete assert.
	// assert(0);
	if(!rPath.empty())
	{	// Find the beginning of the extension
		int nDotPosition = rPath.rfind('.');
		if(nDotPosition == std_string::npos)	// rPath has no extension
		{
			rPath += '.';
			rPath += newExtension;	// the new one is appended
		}
		else
		{
			nDotPosition++;
			int nNumToReplace = rPath.size() - nDotPosition;
			rPath.replace(nDotPosition, nNumToReplace, newExtension);
		}
	}
}


// this function is here because all the functions I could
// find provided by Microsoft for checking status (_stat(), _access() etc.)
// seemed to just check the dos attrib flag.  This does not work if the 
// file is on a network drive which is shared as read only, but does not
// have the read only attrib flag set.  Therefore I actually attempt to 
// open the file in various modes to see if it fails. ka
int FileAccess(const std_string &fileName)
{
#if !defined(MAX_PATH)
	#define MAX_PATH          260
#endif // MAX_PATH
	char pFilePath[MAX_PATH];
	
	int status = RBT_READ_AND_WRITE;
	std_string aFileName = fileName;
	if (fileName[0] == '\0')
		aFileName = "\\UNTITLED.CEG";
	
	FILE *testFile;
	fopen_s(&testFile, aFileName.c_str(), "r");
	if(!testFile)
		status |= RBT_FILE_NOT_FOUND;
	else
	{
		fclose(testFile);
		testFile = NULL;
	}

	fopen_s(&testFile, aFileName.c_str(), "a+");
	if(!testFile && !(status & RBT_FILE_NOT_FOUND))
	{
		status |= RBT_FILE_READ_ONLY;
	}
	if(testFile)
	{
		fclose(testFile);
		testFile = NULL;
		if(status & RBT_FILE_NOT_FOUND)
			remove(aFileName.c_str());
	}

	// create a unique name for a temporary file
	std_string curPath = aFileName;
	int pathLen = curPath.length();
	int nPos = curPath.find_last_of('.');
	if(curPath[0] != '*')		// infinite loop in New Ceg
		assert(nPos != std_string::npos);
	if (nPos == std_string::npos || curPath[0] == '*')
	{
		status |= RBT_INVALID_FILENAME;
		curPath = "foo.ceg";
	}
	else
	{
		curPath = curPath.substr(nPos);
	}
	pathLen = curPath.length();
	assert(pathLen);

	nPos = curPath.rfind('\\');
	if (nPos == std_string::npos)
		nPos = curPath.rfind('.');
	assert(nPos != std_string::npos);
	if (nPos == std_string::npos)
		nPos = 0;
	nPos++;
	
	int patternLen = pathLen - nPos;

	curPath = curPath.substr(nPos);
	curPath += "XXXXXXXX";
	strcpy_s(pFilePath, curPath.length() + 1, curPath.c_str());
	_mktemp_s(pFilePath, curPath.length() + 1);

	// Attempt to create a temporary file
	fopen_s(&testFile, pFilePath, "w");
	if(testFile)
	{	// Directory is writable
		fclose(testFile);
		testFile = NULL;
		remove(pFilePath);
	}
	else
	{
		// an error occurred.  Of course we can't 
		// find out what that error was
		status += RBT_DRIVE_READ_ONLY;
	}

	return status;
} // FileAccess()

bool ValidateForReading(const std_string &fileName)
{
	int status = FileAccess(fileName);
	bool retval = true;

	if((status & RBT_INVALID_FILENAME) || (status & RBT_FILE_NOT_FOUND))
	{
		retval = false;
	}
	return retval;
} // ValidateForReading()

bool ValidateForWriting(const std_string &fileName)
{
	int status = FileAccess(fileName);
	bool retval = true;

	if((status & RBT_INVALID_FILENAME) || (status & RBT_SOMETHING_READ_ONLY)) 
	{
		retval = false;
	}
	return retval;
} // ValidateForWriting()

bool ValidateForReadingAndWriting(const std_string &fileName)
{
	int status = FileAccess(fileName);
	bool retval = true;

	if( ! (status == RBT_READ_AND_WRITE))
	{
		retval = false;
	}
	return retval;
} // ValidateForReadingAndWriting()

// This is the same as ValidateForWriting, except it doesn't
// care if the file exists.
bool DeviceReadOnly(const std_string &fileName)
{
	int status = FileAccess(fileName);
	bool retval = false;

	if((status & RBT_SOMETHING_READ_ONLY)) 
	{
		retval = true;
	}
	return retval;
} // ValidateForWriting()

/////////////////////////////////////////////////////////////////////////////

using namespace std;

std_string FormatTimeString(const time_t &rRawTime)
{
	tm when;
	_tzset();
	errno_t err = localtime_s(&when, &rRawTime);
	assert(EINVAL != err);

	std_string sAM_PM = " AM";
	unsigned int unHour = when.tm_hour;
	if(unHour > 11)
	{
		sAM_PM = " PM";
	}
	if(unHour > 12)
	{
		unHour -= 12;
	}
	if(unHour == 0)
	{
		unHour = 12;
	}

	ostringstream os;
	os << when.tm_mon + 1 
		<< "/"  
		<< when.tm_mday 
		<< "/" 
		<< when.tm_year + 1900 
		<< " " 
		<< unHour 
		<< ":" 
		<< setw(2) << setfill('0') << when.tm_min 
		<< ":" 
		<< setw(2) << setfill('0') << when.tm_sec 
		<< sAM_PM;
	std_string sFormattedTime = os.str();
	return sFormattedTime;
}

time_t StcGetCurrentRawTime()
{
	time_t rawtime;
	time ( &rawtime );
	return rawtime;
}

std_string StcGetCurrentTime()
{
	time_t rawtime = StcGetCurrentRawTime();
	return FormatTimeString(rawtime);
}

LongLongTime::LongLongTime()
{
	dwLowDateTime = 0l;
	dwHighDateTime = 0l;
}

LongLongTime::LongLongTime(time_t tt)
{
	dwLowDateTime = (DWORD)tt;
	dwHighDateTime = tt >> 32;
}

LongLongTime& LongLongTime::operator=(time_t tt)
{
	dwLowDateTime = (DWORD)tt;
	dwHighDateTime = tt >> 32;
	return self;
}

time_t LongLongTime::GetUnixTime()
{
	time_t tt = dwHighDateTime;
	tt = tt << 32;
	tt |= dwLowDateTime;
	return tt;
}

std_string FormatElapsedTime(long aTime)
{
	long elapHours,elapMins,elapSecs;
	elapHours = aTime / 3600;
	elapMins  = (aTime - (elapHours * 3600)) / 60;
	elapSecs  = (aTime - (elapHours * 3600) - (elapMins * 60)); 

	ostringstream os;
	os << setw(2) << setfill('0') << elapHours 
		<< ":" 
		<< setw(2) << setfill('0') << elapMins 
		<< ":" 
		<< setw(2) << setfill('0') << elapSecs;
	std_string sFormattedTime = os.str();
	return sFormattedTime;
}

