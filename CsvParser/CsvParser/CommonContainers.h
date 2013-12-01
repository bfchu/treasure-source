/////////////////////////////////////////////////////////////////////////////
// @doc CommonContainers
// @module CommonContainers.h | Structures, defines, etc. available everywhere.
// 
// 
// Copyright © S9N9S2012  All rights reserved.
//
/////////////////////////////////////////////////////////////////////////////
// 
// Author: Kevin Ablett
// <nl>Created: 10/11/02
// 
/////////////////////////////////////////////////////////////////////////////
//
///////////////////////////////////////////////////////////////////////////////


#ifndef COMMON_CONTAINERS_H
#define COMMON_CONTAINERS_H

// --- includes ---------------------------------------------------------------

#include <vector>
#include <set>
#include <algorithm>

// --- typedefs ------------------------------------------------------

// See the Chapter in Meyers' Effective STL called Avoid vector<bool>
typedef std::vector<char> bool_array;
typedef std::vector<int> int_array;
typedef std::vector<unsigned int> uint_array;
typedef std::vector<uint_array> Uint2DArray;
typedef std::vector<unsigned long> ulong_array;
typedef int_array index_array;
typedef std::vector<char> char_array;

typedef std::set<int> index_set;
typedef std::set<int>::iterator IndexSetIterator;
typedef std::set<int>::const_iterator ConstIndexSetIterator;
typedef std::pair<unsigned int, unsigned int> PairOfUints;

#endif // COMMON_CONTAINERS_H

///////////////////////////////////////////////////////////////////////////
