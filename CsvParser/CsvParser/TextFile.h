/////////////////////////////////////////////////////////////////////////////
// @doc CTextFile
// @module TextFile.h | Class Template component.
// 
// TextFile is used to retrieve lines of text one at a time from a 
// text file until the end of the file is reached. It works properly 
// whether the lines are terminated with CR (Mac style), 
// CR LF (Windows style), or LF (UNIX style). The final line can be 
// terminated by the end of the file rather than by a line terminator.

// SoftTest<tm>
// Copyright © S9N9S2013  All rights reserved.
//
/////////////////////////////////////////////////////////////////////////////
// 
// Author: Shok Ting Ch'ng
// <nl>Created: 9/22/98
// 
/////////////////////////////////////////////////////////////////////////////
//
//	$History: TextFile.h $
// 
// *****************  Version 3  *****************
// User: Kevin Ablett Date: 7/30/03    Time: 5:58p
// Updated in $/Code/RBT_6_0/Common
// Update copyright notices.
// 
// *****************  Version 2  *****************
// User: Kevin Ablett Date: 6/25/03    Time: 10:54a
// Updated in $/Code/RBT_6_0/Common
// Added capability to write files.
// 
// *****************  Version 1  *****************
// User: Kevin Ablett Date: 6/05/03    Time: 5:55p
// Created in $/Code/RBT_6_0/Common
//
//*****************  Version 3  *****************
//User: Kevin Ablett Date: 4/09/00    Time: 10:15a
//Updated in $/SoftTest/TraceProvider/public
//Added FileIsCorrupt exception
//
//*****************  Version 2  *****************
//User: Kevin Ablett Date: 10/02/98   Time: 6:57p
//Updated in $/SoftTest/Rel_5_3/public
//Stage two of linking with caliber.  Put a grid in the dialog.  Open the
//file
//read the lines of text, parse them, populate the grid with the proper
//fields.
//Hook up the grid so it activates the link button when a row is
//selected.
//Hook up sorting, so the grid can be sorted by date or name.
//
//*****************  Version 1  *****************
//User: Kevin Ablett Date: 9/30/98    Time: 2:40p
//Created in $/SoftTest/Rel_5_3/public
//
///////////////////////////////////////////////////////////////////////////////


#ifndef TEXTFILE_H
#define TEXTFILE_H

// --- includes ---------------------------------------------------------------

#include <string>
#include <fstream>

#include "Generic.h"

// --- Data Declarations ------------------------------------------------------

// @class Describe the class here.
class CTextFile
{
public:
// @cmember 
	CTextFile();	// Default constructor.
//	If the file inFilename exists and can be opened for reading this 
//	prepares the file so that lines of text can be read from it. 
//	Otherwise  an InvalidFile exception is thrown. 
	CTextFile( const std_string& inFilename );
// @cmember 
//	If the end of the file has not been reached this returns the next 
//	line of text from the file. Otherwise a ReadPastEOF exception is 
//	thrown. The line terminator is not included with the line.
	std_string NextLine();

	bool CloseFile();

// @cmember 
//	This returns true if the end of the file has been reached 
//	(i.e. no more lines can be read), and false otherwise.
	bool IsAtEndOfFile() const;

// @cmember 
//	This returns true if the file has been corrupted, 
//	and false otherwise.
	bool FileIsCorrupt() const;

// Access functions
public:
	virtual const std_string &GetFileName();
	virtual void SetFileName(const _TCHAR *pFileName);

///////////////////////////// Data ///////////////////////////////////////
protected:
	std_string m_sFileName;
	std_stream m_aFileStream;
}; // class CTextFile

// 	InvalidFile is thrown to indicate that a file could not be opened for use as a TextFile.

class InvalidFile
{
public:
// @cmember 
//	Constructs an object with the message inErrorMessage.
	InvalidFile( const std_string& inErrorMessage );
// @cmember
//	Returns the error message with which this object was constructed.
	std_string ErrorMessage() const;

private:
	std_string mErrorMessage;
};

// ReadPastEOF is thrown to indicate that an attempt was made to 
// read from the file after the end of the file was reached.

class ReadPastEOF
{
public:
// @cmember 
//	Constructs an object with the message inErrorMessage.
	ReadPastEOF ( const std_string& inErrorMessage );
// @cmember 
//	Returns the error message with which this object was constructed.
	std_string ErrorMessage() const;

private:
	std_string mErrorMessage;
};

class CorruptFile
{
public:
// @cmember 
//	Constructs an object with the message inErrorMessage.
	CorruptFile( const std_string& inErrorMessage );
// @cmember
//	Returns the error message with which this object was constructed.
	std_string ErrorMessage() const;

private:
	std_string mErrorMessage;
};

inline const std_string &CTextFile::GetFileName()
{
	return m_sFileName;
}

inline void CTextFile::SetFileName(const _TCHAR *pFileName)
{
	m_sFileName = pFileName;
}

inline bool CTextFile::CloseFile()
{
	m_aFileStream.close();
	return !m_aFileStream.is_open();
}

#endif // TEXTFILE_H

///////////////////////////////////////////////////////////////////////////





