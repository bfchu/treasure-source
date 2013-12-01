/////////////////////////////////////////////////////////////////////////////
// ----------------------------------------------------------------------------
//							AA00_MasterTestFile.cpp     
// ----------------------------------------------------------------------------
//
// 
// Copyright © S9N9S2013  All rights reserved.
// 
// Author: Kevin Ablett
//
/////////////////////////////////////////////////////////////////////////////

#define BOOST_TEST_MODULE MyTests
#include "UnitTesting.h"

#include <locale>

#include "TextFile.h"
#include "Tokenizr.h"

/////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////

BOOST_AUTO_TEST_SUITE( Master_suite )

BOOST_AUTO_TEST_CASE(testBoost)
{
	BOOST_CHECK_EQUAL(0, 0.000000000);
}

BOOST_AUTO_TEST_CASE(testFileOpen)
{
	bool bTestFileWorks = true;
	try
	{
		CTextFile aFile("C:\\Users\\Brian\\Documents\\GitHub\\treasure-source\\CsvParser\\DataFiles\\spell_full.csv");
		std_string sCurrentLine = aFile.NextLine();
		BOOST_CHECK_EQUAL(sCurrentLine, "	dlow	dhigh	spellName	spellClass	spellLevel");

		std::ofstream outFile;
		outFile.open("C:\\Users\\Brian\\Documents\\GitHub\\treasure-source\\CsvParser\\DataFiles\\spell_full.dat", std::ios::out) ;

		sCurrentLine = aFile.NextLine();
		unsigned int unTabPos = sCurrentLine.rfind('	');
		if(std_string::npos != unTabPos)
		{
			std_string sFirstPart = sCurrentLine.substr(0, unTabPos + 1);
			BOOST_CHECK_EQUAL(sFirstPart,"	9999	9999	Acid Arrow	");

			std_string sLastPart = sCurrentLine.substr(unTabPos + 1);
			//BOOST_CHECK_EQUAL(sLastPart, "sorcerer/wizard 2, magus 2");
			CSTokenizer aTokenizer(sLastPart, ",");
			unsigned int unTokenCount = aTokenizer.NumTokens();
			BOOST_CHECK_EQUAL(2, unTokenCount);
			for(unsigned int unToken = 0; unToken < unTokenCount; ++unToken)
			{
				std_string sNextPart = aTokenizer.NextToken();
				StripLeadingWhiteSpace(sNextPart);
				std_string sLine = sFirstPart + sNextPart;
				outFile << sLine << '\n';
				//BOOST_CHECK_EQUAL(sLine, "foo");
			}
		}
		while (!aFile.IsAtEndOfFile())
		{
			sCurrentLine = aFile.NextLine();
			unsigned int unTabPos = sCurrentLine.rfind('	');
			if(std_string::npos != unTabPos)
			{
				std_string sFirstPart = sCurrentLine.substr(0, unTabPos + 1);
				//BOOST_CHECK_EQUAL(sFirstPart,"	9999	9999	Acid Arrow	");

				std_string sLastPart = sCurrentLine.substr(unTabPos + 1);
				//BOOST_CHECK_EQUAL(sLastPart, "sorcerer/wizard 2, magus 2");
				CSTokenizer aTokenizer(sLastPart, ",");
				unsigned int unTokenCount = aTokenizer.NumTokens();
				//BOOST_CHECK_EQUAL(2, unTokenCount);
				for(unsigned int unToken = 0; unToken < unTokenCount; ++unToken)
				{
					std_string sNextPart = aTokenizer.NextToken();
					StripLeadingWhiteSpace(sNextPart);
					std_string sLine = sFirstPart + sNextPart;
					outFile << sLine << '\n';
					//BOOST_CHECK_EQUAL(sLine, "foo");
				}

			}
		}

	}
	catch(...)
	{
		bTestFileWorks = false;
	}
	BOOST_CHECK_EQUAL(bTestFileWorks, true);

}

BOOST_AUTO_TEST_SUITE_END()
