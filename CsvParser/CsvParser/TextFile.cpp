// ----------------------------------------------------------------------------
//							TextFile.cpp     
// @doc CTextFile
// @module TextFile.cpp | Implementation of the <c CTextFile>
// class.
// 
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
//	$History: TextFile.cpp $
// 
///////////////////////////////////////////////////////////////////////////////

// --- includes ---------------------------------------------------------------

#include "TextFile.h"

// --- Data Declarations ------------------------------------------------------

// --- Function Definitions ---------------------------------------------------

const _TCHAR CR = '\r';	// Carriage Return
const _TCHAR LF = '\n';	// Line Feed

// @mfunc Constructor.
// @parm A const reference to the name of the file that it is opening
CTextFile::CTextFile()
{
}

// @mfunc Constructor.
// @parm A const reference to the name of the file that it is opening
CTextFile::CTextFile( const std_string& inFilename )
	: m_sFileName( inFilename )
{
	//throw InvalidFile( "Cannot open file " + m_sFileName );
	m_aFileStream.open( m_sFileName.c_str() );
	if (!m_aFileStream)
	{
		throw InvalidFile("File open failed.");
	}
}

// @mfunc Get the next string in the file
// @rdesc The next string in the file
std_string CTextFile::NextLine()
{
	if (IsAtEndOfFile())
	{
		throw ReadPastEOF("End of file.");
	}
	if(FileIsCorrupt())
	{
		throw CorruptFile("File is corrupt.");
	}

	std_string result;
	TCHAR aChar;
	aChar = _TCHAR(m_aFileStream.get());
	while ((!IsAtEndOfFile()) && (aChar != CR) && (aChar != LF))	// A line can end in CR, LF, or EOF
	{
		if(FileIsCorrupt())
		{
			throw CorruptFile("File is corrupt.");
		}
		result += aChar;
		aChar = _TCHAR(m_aFileStream.get());
	}
	// If we detect CR LF or LF CR we will remove the second character of the sequence
	// We  might see LF CR because the Mac changes CR to LF and LF to CR when it reads
	// a character from a text file.
	if (((aChar == CR) && (m_aFileStream.peek() == LF)) || ((aChar == LF) && (m_aFileStream.peek() == CR)))
	{
		m_aFileStream.get();
		if(FileIsCorrupt())
		{
			throw CorruptFile("File is corrupt.");
		}
	}

	return result;
}

// @mfunc Check to see if the end of the file has been reached
// @rdesc true if at end of file, false otherwise
bool CTextFile::IsAtEndOfFile() const
{
	/*	int test = m_aFileStream.rdstate();
	test = m_aFileStream.rdstate() & m_aFileStream.badbit;
	test = m_aFileStream.rdstate() & m_aFileStream.failbit;
	*/	return !m_aFileStream;
}

// @mfunc Check to see if the end of the file has been reached
// @rdesc true if at end of file, false otherwise
bool CTextFile::FileIsCorrupt() const
{
	bool test = (m_aFileStream.rdstate() & m_aFileStream.badbit) != 0;
	//	test = m_aFileStream.rdstate() & m_aFileStream.failbit;
	return test;
}

// @mfunc Constructor.
// @parm A const reference to the error message
InvalidFile::InvalidFile( const std_string& inErrorMessage )
	: mErrorMessage( inErrorMessage )
{
}

// @mfunc Get the error message
// @rdesc the error message
std_string InvalidFile::ErrorMessage() const
{
	return mErrorMessage;
}

// @mfunc Constructor.
// @parm A const reference to the error message
ReadPastEOF::ReadPastEOF( const std_string& inErrorMessage )
	: mErrorMessage( inErrorMessage )
{
}

// @mfunc Get the error message
// @rdesc the error message
std_string ReadPastEOF::ErrorMessage() const
{
	return mErrorMessage;
}

// @mfunc Constructor.
// @parm A const reference to the error message
CorruptFile::CorruptFile( const std_string& inErrorMessage )
	: mErrorMessage( inErrorMessage )
{
}

// @mfunc Get the error message
// @rdesc the error message
std_string CorruptFile::ErrorMessage() const
{
	return mErrorMessage;
}






