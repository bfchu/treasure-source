/////////////////////////////////////////////////////////////////////////////
//							std_string.h     
// 
// ----------------------------------------------------------------------------
// 
// Copyright © S9N9S2013  All rights reserved.
// 
// Author: Kevin Ablett
// ----------------------------------------------------------------------------
// 
// $History: std_string.h $
// 
/////////////////////////////////////////////////////////////////////////////

#if !defined(STD_STRING_H)
#define STD_STRING_H

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include <tchar.h>
#include <cassert>
#include <string>
#include <vector>
#include <set>
#include <algorithm>

/////////////////////////////////////////////////////////////////////////////

#if defined (_MSC_VER)
	#include <tchar.h>
	#include "stdafx.h"
	typedef std::basic_string<_TCHAR> std_string;
	typedef std::basic_string<_TCHAR>::size_type StringSizeType;
#else // _MSC_VER
	typedef std::basic_string<char> std_string;
	typedef std::basic_string<char>::size_type StringSizeType;
#endif // _MSC_VER

#if defined (HRESULT)
HRESULT __fastcall AnsiToUnicode(LPCSTR pszA, LPOLESTR* ppszW);
HRESULT __fastcall UnicodeToAnsi(LPCOLESTR pszW, LPSTR* ppszA);
#endif // HRESULT

typedef std::vector<std_string> string_array;
typedef std::set<std_string> string_set;
typedef std::set<std_string>::iterator StringSetIterator;

#include "StringProcessing.h"

#endif // !defined(STD_STRING_H)
