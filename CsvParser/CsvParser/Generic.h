/////////////////////////////////////////////////////////////////////////////
// @doc Generic
// @module Generic.h | Useful defines, enums and data types
//  that are not part of any class, and not platform specific.
// 
// SoftTest<tm>
// Copyright © S9N9S2012  All rights reserved.
//
/////////////////////////////////////////////////////////////////////////////
// 
// Author: Kevin Ablett
// <nl>Created: 9/18/98
// 
/////////////////////////////////////////////////////////////////////////////
//
//	$History: Generic.h $
// 
///////////////////////////////////////////////////////////////////////////////


#ifndef ST_GENERIC_H
#define ST_GENERIC_H

#pragma warning (disable : 4786)

// --- includes ---------------------------------------------------------------

#include <cassert>
#include <string>
#include <vector>
#include <deque>
#include <set>
#include <map>
#include <fstream>
#include "std_string.h"
#include "CommonContainers.h"

#include "Platform.h"

// --- defines, typedefs and enums ------------------------------------------------------

const int INVALID_COUNT = -1;

// DEBUGGING defined here
#if defined _DEBUG
	#define DEBUGGING
#endif // _DEBUG

enum ST_SourceType
{
	LOCAL,
	DATA_DICTIONARY,
	ALIAS,
	SUBGRAPH
};

#define OFF   0
#define ON    1

#if !defined self
	#define self (*this)
#endif

#define	RBT_READ_AND_WRITE		0
#define	RBT_FILE_READ_ONLY		1
#define	RBT_DRIVE_READ_ONLY		2
#define	RBT_SOMETHING_READ_ONLY	3
#define	RBT_FILE_NOT_FOUND		4
#define	RBT_INVALID_FILENAME	8

// --- Function Declarations ---------------------------------------------------
unsigned int GetFileExtension(const std_string &rPath, std_string &rExtension);

extern int FileAccess(const std_string &fileName);
extern bool ValidateForReading(const std_string &fileName);
extern bool ValidateForWriting(const std_string &fileName);
extern bool ValidateForReadingAndWriting(const std_string &fileName);
extern bool DeviceReadOnly(const std_string &fileName);

extern bool DisallowExtension(const std_string &path, const std_string &rExtension);
extern void ReplaceExtension(std_string &path, const std_string &newExtension);
extern std_string ShortTimer(time_t* aTime);
extern bool QueryExtension(const std_string &rFileName, const std_string &rExtension);

std_string FormatTimeString(const time_t &rRawTime);
time_t StcGetCurrentRawTime();
std_string StcGetCurrentTime();

// PointerCompare is a functor, used by sort, for situations where we have
// a container of pointers, and we want to sort them according to the 
// items that are pointed at.
template<class ArgType>
class PointerCompare
{
public:
	bool operator()(const ArgType* pArg1, const ArgType* pArg2){return *pArg1 < *pArg2;}
};

// Function templates:
template<class containerType>
inline void MyEraseRemoveIdiom(containerType &rVector, unsigned int nIndex)
{
	unsigned int unNumItems = rVector.size();
	assert(nIndex < unNumItems);

	// use my erase-remove idiom which works with indices
	for(unsigned int ii = nIndex + 1; ii < unNumItems; ii++)
		rVector[ii - 1] = rVector[ii];
	rVector.erase(rVector.end() - 1, rVector.end());
}

template<class containerType, class ArgTypeValue>
inline void StandardEraseRemoveIdiom(containerType &rVector, ArgTypeValue val)
{
	rVector.erase(remove(rVector.begin(), rVector.end(), val), rVector.end());
}

class LongLongTime
{
public:
	LongLongTime();
	LongLongTime(time_t tt);
	LongLongTime& operator=(time_t tt);
	time_t GetUnixTime();
	unsigned long dwLowDateTime;
	unsigned long dwHighDateTime;
};

std_string FormatElapsedTime(long aTime);

#endif // ST_GENERIC_H

///////////////////////////////////////////////////////////////////////////
