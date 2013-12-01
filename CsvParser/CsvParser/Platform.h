/////////////////////////////////////////////////////////////////////////////
// Instructions for converting this template into a command header file:
// ----------------------------------------------------------------------------
//							Platform.h     
// ----------------------------------------------------------------------------
// 
// 
// Copyright © S9N9S2012  All rights reserved.
// 
// Author: Kevin Ablett
// 
/////////////////////////////////////////////////////////////////////////////

#if !defined PARSER_PLATFORM_HPP
#define PARSER_PLATFORM_HPP

#include <string>
#include <vector>
#include "std_string.h"

#define _TCHAR char
#define TCHAR char
#define DWORD long
#define LPFILETIME long*
#define LPSYSTEMTIME long*
#define MB_OK 0
#define MB_ICONERROR 0

#if !defined _WINDEF_
struct HINSTANCE__ { int unused; }; typedef struct HINSTANCE__ *HINSTANCE;
#define _WINDEF_
#endif

#if !defined MessageBoxDefined
inline void MessageBox(int, int)
{
}

inline void MessageBox(int, const char *,  char *, int)
{
}
#define MessageBoxDefined
#endif // MessageBoxDefined

typedef std::basic_fstream<char> std_stream;

#if defined ASSERT
	#define IF_ASSERT(f) \
 		ASSERT((f)); \
		if((f))

	#define IF_NOT_ASSERT(f) \
		ASSERT((f)); \
		if(!(f))  // Error condition
#else // ASSERT
#include <crtdbg.h>
#define IF_ASSERT(f) \
 		_ASSERTE((f)); \
		if((f))

	#define IF_NOT_ASSERT(f) \
		_ASSERTE((f)); \
		if(!(f))  // Error condition
#endif // ASSERT

#define OFF   0
#define ON    1

// --- defines & enums -------------------------------------------------------

#ifndef max
#define max(a,b)	(((a) > (b)) ? (a) : (b))
#endif

#ifndef min
#define min(a,b)	(((a) < (b)) ? (a) : (b))
#endif

// --- classes ---------------------------------------------------------------

#endif //PARSER_PLATFORM_HPP







