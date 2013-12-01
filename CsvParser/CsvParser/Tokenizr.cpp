// ----------------------------------------------------------------------------
//							Tokenizr.cpp     
// @doc CSTokenizer
// @module Tokenizr.cpp | Implementation of the <c CSTokenizer>
// class.
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
//	$History: Tokenizr.cpp $
// 
///////////////////////////////////////////////////////////////////////////////

// --- includes ---------------------------------------------------------------

#include "Tokenizr.h"

// --- Data Declarations ------------------------------------------------------

// --- Function Definitions ---------------------------------------------------

CSTokenizer::CSTokenizer(const std_string& inToBeTokenized, const std_string& inDelimiters)
: 
mToBeTokenized(inToBeTokenized),
mDelimiters(inDelimiters),
mCurrentPos(0),
mCurrentNumberOfTokens(0),
mbTreatMultipleDelimitersAsSingle(true)
{
	if (inDelimiters.empty()) 
		throw EmptyDelimiterString( "You input an \"" + mDelimiters + "\" delimiter");
}

CSTokenizer& CSTokenizer::operator=(const std_string &rSrc)
{
	mToBeTokenized = rSrc;
	mCurrentPos = 0;
	mCurrentNumberOfTokens = 0;
	return self;
}

TokenEnds CSTokenizer::GetTokenEnds(StringSizeType begin) const
{			
	StringSizeType end;
	StringSizeType previous;
	TokenEnds oTokenEnds;

	if (mbTreatMultipleDelimitersAsSingle == true)
	{
		do
		{
			previous = begin;
			end = mToBeTokenized.find_first_of(mDelimiters,begin);
			if (end != std_string::npos)
				begin = end + 1;
			// if there are 2 delimiters in a row, skip to next
		} while ((end != std_string::npos) && (end == previous)); 
		begin = previous;
	}
	else
	{
		end = mToBeTokenized.find_first_of(mDelimiters,begin);
	}
	oTokenEnds.first = begin;
	oTokenEnds.second = end;

	return oTokenEnds;
}

std_string CSTokenizer::NextToken()
{			
	TokenEnds oTokenEnds = GetTokenEnds(mCurrentPos);
	StringSizeType begin = oTokenEnds.first;
	StringSizeType end = oTokenEnds.second;
	std_string aToken = mToBeTokenized.substr(begin, end - begin);
	if(std_string::npos == end)
	{
		StringSizeType aStringLength = mToBeTokenized.size();
		if(aStringLength)
			mCurrentPos = aStringLength - 1;
	}
	else
		mCurrentPos = end + 1;
	
	return aToken;
}

std_string CSTokenizer::NextToken(const std_string& inNewDelimiters)  // uses new delimiters	 
{
	mDelimiters = inNewDelimiters;
	return NextToken();	
}

int CSTokenizer::NumTokens()
{		 		 
	mCurrentNumberOfTokens = 0;
	TokenEnds oTokenEnds;
	StringSizeType end = (StringSizeType)-1;
	
	do
	{
		oTokenEnds = GetTokenEnds(end + 1);
		end = oTokenEnds.second;
		mCurrentNumberOfTokens++;
	} while (end != std_string::npos);
	
	return mCurrentNumberOfTokens;
}

std_string CSTokenizer::RemainderOfOriginal() const
{
	std_string remaindingString = mToBeTokenized.substr(mCurrentPos, std_string::npos);
	return remaindingString;
}	


std_string CSTokenizer::UnTokenizedOriginal() const
{
	return mToBeTokenized;
}

std_string CSTokenizer::CurrentDelimiters() const
{
	return mDelimiters;
}

void CSTokenizer::SetDelimiters(const std_string& inDelimiters)
{
	mDelimiters = inDelimiters;
}

void CSTokenizer::SetDelimitersState(bool inDelimiterValue)
{
	mbTreatMultipleDelimitersAsSingle = inDelimiterValue;
}			
	
bool CSTokenizer::GetDelimitersState() const
{
	return mbTreatMultipleDelimitersAsSingle;
}

void CSTokenizer::SkipWhiteSpace()
{
	StringSizeType end = mToBeTokenized.size();
	while ((mCurrentPos != end) && ((unsigned char)(isspace(mToBeTokenized[mCurrentPos]))))
		mCurrentPos++;
}

void CSTokenizer::SkipAChar(char cChar)
{
	StringSizeType end = mToBeTokenized.size();
	if((mCurrentPos != end) && (cChar == mToBeTokenized[mCurrentPos]))
		mCurrentPos++;
}

EmptyDelimiterString::EmptyDelimiterString( const std_string& inErrorMessage )
	: mErrorMessage( inErrorMessage )
{
}

std_string EmptyDelimiterString::ErrorMessage() const
{
	return mErrorMessage;
}
