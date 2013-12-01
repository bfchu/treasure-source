/////////////////////////////////////////////////////////////////////////////
//							StringProcessing.h     
// 
// ----------------------------------------------------------------------------
// 
// Copyright © S9N9S2013  All rights reserved.
// 
// Author: Kevin Ablett
// ----------------------------------------------------------------------------
/////////////////////////////////////////////////////////////////////////////

#if !defined(STRING_PROCESSING_H)
#define STRING_PROCESSING_H

#include <sstream>

/////////////////////////////////////////////////////////////////////////////

void StripLeadingWhiteSpace(std_string &rString);
unsigned int CountLeadingWhitespace(const std_string &rString);
void StripTrailingWhiteSpace(std_string &rString);
unsigned int CountTrailingWhitespace(const std_string &rString);
bool DetectWhitespace(const std_string &rString);
void StripAllWhiteSpace(std_string &rString);
char GetLastCharacter(const std_string &rString);
unsigned int CountSingleQuotes(const std_string &rString);
unsigned int CountParentheses(const std_string &rString);
unsigned int CountInstancesOfChar(const std_string &rString, char aChar);
bool IsBlank(std_string aString);
unsigned int GetNumericalSuffixFromString(const std_string &rString);
void StripElipsis(std_string &rString);
void StripHyphen(std_string &rString);
void StripFormatParameters(std_string &rString);

inline void RaiseCase(std_string &rString)
{
	std::transform(rString.begin(), rString.end(),	// source
		rString.begin(),							// destination
		toupper);									// operation
}

inline void LowerCase(std_string &rString)
{
	std::transform(rString.begin(), rString.end(),	// source
		rString.begin(),							// destination
		tolower);									// operation
}

template<class TypeOfNumber> void NumberToString(std_string &rString, TypeOfNumber aVal)
{
	std::ostringstream os;
	os << aVal;
	rString = os.str();
}

template<class TypeOfNumber> void AppendNumber(std_string &rString, TypeOfNumber aVal)
{
	std_string sValue;
	NumberToString(sValue, aVal);
	rString += sValue;
}

template <typename T> T StringToNumber(const std_string &Text, T defValue = T())
{
	std::stringstream ss(Text);
	T result;
	return ss >> result ? result : 0;
}

#endif // !defined(STRING_PROCESSING_H)
