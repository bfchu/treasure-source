// ----------------------------------------------------------------------------
//							std_string.cpp     
// Implementation of various classes.
// 
// 
// Copyright © S9N9S2013  All rights reserved.
//
/////////////////////////////////////////////////////////////////////////////
// 
// Author: Kevin Ablett
// <nl>Created: 5/24/09
// 
/////////////////////////////////////////////////////////////////////////////
//
//	$History: std_string.cpp $
// 
///////////////////////////////////////////////////////////////////////////////

// --- includes ---------------------------------------------------------------

#include <math.h> // for log10
#include <algorithm>
#include <stdlib.h>
#include <string.h>

#include "std_string.h"

// --- Data Declarations ------------------------------------------------------
void StripLeadingWhiteSpace(std_string &rString)
{
	unsigned int unNumSpaces = CountLeadingWhitespace(rString);
	if(unNumSpaces)
	{
		unsigned int unCharCount = rString.size();
		std_string sBuffer = rString.substr(unNumSpaces, unCharCount - unNumSpaces);
		rString = sBuffer;
	}
}

unsigned int CountLeadingWhitespace(const std_string &rString)
{
	unsigned int unNumSpaces = 0;
	unsigned int unCount = rString.size();
	for(unsigned int cc = 0; cc < unCount; cc++)
	{
		if(isspace((unsigned char)(rString[cc])))
			unNumSpaces++;
		else
			break;
	}
	assert(unNumSpaces <= unCount);
	return unNumSpaces;
}

void StripTrailingWhiteSpace(std_string &rString)
{
	unsigned int unNumSpaces = CountTrailingWhitespace(rString);
	if(unNumSpaces)
	{
		unsigned int unCharCount = rString.size();
		std_string sBuffer = rString.substr(0, unCharCount - unNumSpaces);
		rString = sBuffer;
	}
}

unsigned int CountTrailingWhitespace(const std_string &rString)
{
	unsigned int unNumSpaces = 0;
	int unStartHere = rString.size() - 1;
	for(int cc = unStartHere; cc >= 0; cc--)
	{
		if(isspace((unsigned char)(rString[cc])))
			unNumSpaces++;
		else
			break;
	}
	assert(unNumSpaces <= rString.size());
	return unNumSpaces;
}

void StripAllWhiteSpace(std_string &rString)
{
	if(DetectWhitespace(rString))
	{
		std_string sBuffer;
		unsigned int unCharCount = rString.size();
		sBuffer.reserve(unCharCount);
		for(unsigned int cc = 0; cc < unCharCount; cc++)
		{
			unsigned char aChar = rString[cc];
			if(!isspace(aChar))
				sBuffer.push_back(aChar);
		}
		rString = sBuffer;
	}
}

bool DetectWhitespace(const std_string &rString)
{
	unsigned int unCharCount = rString.size();
	for(unsigned int cc = 0; cc < unCharCount; cc++)
	{
		if(isspace((unsigned char)(rString[cc])))
			return true;
	}
	return false;
}

char GetLastCharacter(const std_string &rString)
{
	char cLast = '\0';
	if(!rString.empty())
	{
		int unLastPos = rString.size() - 1;
		cLast = rString[unLastPos];
	}
	return cLast;
}

unsigned int CountInstancesOfChar(const std_string &rString, char aChar)
{
	unsigned int unNumInstances = 0;
	unsigned int unCount = rString.size();
	for(unsigned int cc = 0; cc < unCount; cc++)
	{
		if(rString[cc] == aChar)
			unNumInstances++;
	}
	assert(unNumInstances <= unCount);
	return unNumInstances;
}

unsigned int CountSingleQuotes(const std_string &rString)
{
	return CountInstancesOfChar(rString, '\'');
}

unsigned int CountParentheses(const std_string &rString)
{
	return CountInstancesOfChar(rString, '(');
}

bool IsBlank(std_string aString)
{
	StripAllWhiteSpace(aString);
	return aString.empty();
}

unsigned int CountTrailingDigits(const std_string &rString)
{
	unsigned int unNumDigits = 0;
	int unStartHere = rString.size() - 1;
	for(int cc = unStartHere; cc >= 0; cc--)
	{
		if(isdigit((unsigned char)(rString[cc])))
			unNumDigits++;
		else
			break;
	}
	assert(unNumDigits <= rString.size());
	return unNumDigits;
}

unsigned int GetNumericalSuffixFromString(const std_string &rString)
{
	unsigned int unValue = 0;
	unsigned int unNumDigits = CountTrailingDigits(rString);
	if(unNumDigits)
	{
		unsigned int unCharCount = rString.size();
		std_string sBuffer = rString.substr(unCharCount - unNumDigits, unCharCount);
		unValue = StringToNumber(sBuffer, unValue);
	}
	return unValue;
}

void StripElipsis(std_string &rString)
{
	int nElipsisPos = rString.find("...");
	if(std_string::npos != nElipsisPos)
	{
		rString = rString.substr(0, nElipsisPos);
	}
}

void StripHyphen(std_string &rString)
{
	int nHyphenPos = rString.find("-");
	if(std_string::npos != nHyphenPos)
	{
		int nLength = rString.size();
		std_string sBuffer = rString.substr(0, nHyphenPos);
		sBuffer += rString.substr(nHyphenPos + 1, nLength);
		rString = sBuffer;
	}
}

void StripFormatParameters(std_string &rString)
{
	int nParamPos = rString.find("%");
	if(std_string::npos != nParamPos)
	{
		int nLength = rString.size();
		std_string sBuffer = rString.substr(0, nParamPos);
		rString = sBuffer;
	}
}
