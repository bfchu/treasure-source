/////////////////////////////////////////////////////////////////////////////
// @doc CSTokenizer
// @module Tokenizr.h | Class Template component.
//A String Tokenizer object is used to extract tokens from a string. 
// 
// SoftTest<tm>
// Copyright © S9N9S2013  All rights reserved.
//
/////////////////////////////////////////////////////////////////////////////
// 
// Author: Shok Ting Ch'ng
// <nl>Created: 9/28/98
// 
/////////////////////////////////////////////////////////////////////////////
//
//	$History: Tokenizr.h $
// 
// *****************  Version 2  *****************
// User: Kevin Ablett Date: 7/30/03    Time: 5:58p
// Updated in $/Code/RBT_6_0/Common
// Update copyright notices.
// 
// *****************  Version 1  *****************
// User: Kevin Ablett Date: 5/08/03    Time: 11:28a
// Created in $/Code/RBT_6_0/Common
//
//*****************  Version 3  *****************
//User: Joe Buller   Date: 12/09/98   Time: 11:57a
//Updated in $/SoftTest/Rel_5_3/public
//Activate Caliber/RMagent logic
//Fix File/New editor color syntax highlighting [5.3 BugRpt #34]
//Add Undo button to toolbar
//Don't show Caliber toolbar button if View/Toolbars not selected 5.3
//BugRpt #1004]
//
//*****************  Version 2  *****************
//User: Kevin Ablett Date: 10/02/98   Time: 6:57p
//Updated in $/SoftTest/Rel_5_3/public
//Stage two of linking with caliber.  Put a grid in the dialog.  Open the file
//read the lines of text, parse them, populate the grid with the proper fields.
//Hook up the grid so it activates the link button when a row is selected.
//Hook up sorting, so the grid can be sorted by date or name.
//
//*****************  Version 1  *****************
//User: Kevin Ablett Date: 9/30/98    Time: 10:28a
//Created in $/SoftTest/Rel_5_3/public
//
///////////////////////////////////////////////////////////////////////////////


#ifndef STOKENIZER_H
#define STOKENIZER_H

// --- includes ---------------------------------------------------------------

#include <string>

#include "generic.h"

// --- Data Declarations ------------------------------------------------------

// Position and Length
typedef std::pair<StringSizeType, StringSizeType> TokenEnds;

// @class Describe the class here.
class CSTokenizer
{
public:
// @cmember Constructor.
//		Precondition:	Two strings are passed in, one a string to be tokenized and
//						the other containing delimiter characters.  If the second
//						argument is not specified, the delimiters default to to
//						space and tab characters and consecutive delimiters are treated
//						as single.
//		Postcondition:	Create a string tokenizer object and initialze its member variables.				
	CSTokenizer(const std_string& inToBeTokenized, const std_string& inDelimiters  = "\",");

//	MODIFICATION FUNCTIONS
	CSTokenizer& operator=(const std_string &rSrc);

//	@cmember  uses delimiters set in the constructor
//		Postcondition:	returns a string containing the next token if there is one.  
	std_string NextToken();
	std_string ToString(){return mToBeTokenized;}

// @cmember uses new delimiters	 
//		Precondition:	A string containig a new set of delimiters is passed in.
//						If not provided, will default to calling NextToken().
//		Postcondition:	Returns a string containing the next token if there is one. 
	std_string NextToken(const std_string& inNewDelimiters);

// @cmember
//		Postcondition:	Returns the number of tokens that can still be extracted from the
// 						string characters. The new delimiter set replaces the old one before
// 						the next token is extracted.
	int NumTokens();

// @cmember
//		Precondition:	A bool value is passed in which determines whether subsequent calls
//						to retrieve the next token treat consecutive delimiters as a single
//						delimiter.
//		Postcondition:	The bool value is set. 
	void SetDelimitersState(bool inDelimiterValue);			
//
//	CONSTANT FUNCTIONS

// @cmember
//		Postcondition:	Returns the original untokenized string. 
	std_string UnTokenizedOriginal() const;

// @cmember
//		Postcondition:	Returns the part of the original string that has, as yet, not been 
//						returned as tokens. 
//						For example, if the original string is "token1,,,token2" and the 
//						delimiter set is "," and one call has been made to the
//						member function that returns a token, then in effect one token 
//						and one delimiter have already been passed over and the part of 
//						the string that remains is ",,token2". 
	std_string RemainderOfOriginal() const;

// @cmember
//		Postcondition:	Returns a string containing the current delimiter characters. 
	std_string CurrentDelimiters() const;
	void SetDelimiters(const std_string& inDelimiters);

// @cmember
//		Postcondition:	Returns a bool that indicates whether calls to retrieve the 
//						next token treat consecutive delimiters as a single delimiter.
	bool GetDelimitersState() const;
	void SkipWhiteSpace();
	void SkipAChar(char cChar);
#ifndef _DEBUG
private:
#endif

// @cmember
	// Get next token's bebinning and end positions
	TokenEnds GetTokenEnds(StringSizeType pos) const;
	// Data members
private:
	std_string mToBeTokenized;
	std_string mDelimiters;	
	StringSizeType mCurrentPos;
	int mCurrentNumberOfTokens;
	bool mbTreatMultipleDelimitersAsSingle;

	// Access functions
public:

}; // class CSTokenizer

//	EmptyDelimiterString is thrown to indicate that am empty delimiter was passed in 
//	for use for the construction of the StringTokenizer object
//	
//	NOTE:
//	The exception will happen only if the following code statements in main.cpp have been
//	commented out:
//					if (inDelimiters.empty())
//						inDelimiters = " \t";
//	and no delimiters was provided by just entering Return but the untokenized string 
//	has been entered.  The code in main.cpp have been set to default to allow for call
//	to NextToke() with empty string as an indication for using default delimiters.	  
//
//	
//	CONTSTRUCTOR
//	EmptyDelimiterString(const string& inErrorMessage);
//	Postcondition:	construct an object with the message inErrorMessage that describes 
//					the exception. 
//
//	string ErrorMessage() const;
//	Postcondition:  Returns the error message used to construct the exception object.

class EmptyDelimiterString
{
public:
	EmptyDelimiterString(const std_string& inErrorMessage);
	std_string ErrorMessage() const;

private:
	std_string mErrorMessage;
};

#endif // STOKENIZER_H

////////////////////////////////////////////////////////////





