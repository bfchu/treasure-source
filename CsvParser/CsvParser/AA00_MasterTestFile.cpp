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

std::ofstream streamRed_Mantis_Assassin2;
std::ofstream streamadept0;
std::ofstream streamadept1;
std::ofstream streamadept2;
std::ofstream streamadept3;
std::ofstream streamalchemist1;
std::ofstream streamalchemist2;
std::ofstream streamalchemist3;
std::ofstream streamalchemist4;
std::ofstream streamalchemist5;
std::ofstream streamalchemist6;
std::ofstream streamantipaladin1;
std::ofstream streamantipaladin2;
std::ofstream streamantipaladin3;
std::ofstream streamantipaladin4;
std::ofstream streambard0;
std::ofstream streambard1;
std::ofstream streambard2;
std::ofstream streambard3;
std::ofstream streambard4;
std::ofstream streambard5;
std::ofstream streambard6;
std::ofstream streamcleric3;
std::ofstream streamcleric_oracle0;
std::ofstream streamcleric_oracle1;
std::ofstream streamcleric_oracle2;
std::ofstream streamcleric_oracle3;
std::ofstream streamcleric_oracle4;
std::ofstream streamcleric_oracle5;
std::ofstream streamcleric_oracle6;
std::ofstream streamcleric_oracle7;
std::ofstream streamcleric_oracle8;
std::ofstream streamcleric_oracle9;
std::ofstream streamdruid0;
std::ofstream streamdruid1;
std::ofstream streamdruid2;
std::ofstream streamdruid3;
std::ofstream streamdruid4;
std::ofstream streamdruid5;
std::ofstream streamdruid6;
std::ofstream streamdruid7;
std::ofstream streamdruid8;
std::ofstream streamdruid9;
std::ofstream streaminquisitor0;
std::ofstream streaminquisitor1;
std::ofstream streaminquisitor2;
std::ofstream streaminquisitor3;
std::ofstream streaminquisitor4;
std::ofstream streaminquisitor5;
std::ofstream streaminquisitor6;
std::ofstream streammagus0;
std::ofstream streammagus1;
std::ofstream streammagus2;
std::ofstream streammagus3;
std::ofstream streammagus4;
std::ofstream streammagus5;
std::ofstream streammagus6;
std::ofstream streamoracle1;
std::ofstream streamoracle2;
std::ofstream streamoracle3;
std::ofstream streamoracle4;
std::ofstream streamoracle5;
std::ofstream streamoracle8;
std::ofstream streampaladin1;
std::ofstream streampaladin2;
std::ofstream streampaladin3;
std::ofstream streampaladin4;
std::ofstream streamranger1;
std::ofstream streamranger2;
std::ofstream streamranger3;
std::ofstream streamranger4;
std::ofstream streamsorcerer_wizard0;
std::ofstream streamsorcerer_wizard1;
std::ofstream streamsorcerer_wizard2;
std::ofstream streamsorcerer_wizard3;
std::ofstream streamsorcerer_wizard4;
std::ofstream streamsorcerer_wizard5;
std::ofstream streamsorcerer_wizard6;
std::ofstream streamsorcerer_wizard7;
std::ofstream streamsorcerer_wizard8;
std::ofstream streamsorcerer_wizard9;
std::ofstream streamsummoner0;
std::ofstream streamsummoner1;
std::ofstream streamsummoner2;
std::ofstream streamsummoner3;
std::ofstream streamsummoner4;
std::ofstream streamsummoner5;
std::ofstream streamsummoner6;
std::ofstream streamwitch0;
std::ofstream streamwitch1;
std::ofstream streamwitch2;
std::ofstream streamwitch3;
std::ofstream streamwitch4;
std::ofstream streamwitch5;
std::ofstream streamwitch6;
std::ofstream streamwitch7;
std::ofstream streamwitch8;
std::ofstream streamwitch9;
std::ofstream streamwizard2;
std::ofstream streamwizard4;
std::ofstream streamwizard6;

std_string stringRed_Mantis_Assassin2;
std_string stringadept0;
std_string stringadept1;
std_string stringadept2;
std_string stringadept3;
std_string stringalchemist1;
std_string stringalchemist2;
std_string stringalchemist3;
std_string stringalchemist4;
std_string stringalchemist5;
std_string stringalchemist6;
std_string stringantipaladin1;
std_string stringantipaladin2;
std_string stringantipaladin3;
std_string stringantipaladin4;
std_string stringbard0;
std_string stringbard1;
std_string stringbard2;
std_string stringbard3;
std_string stringbard4;
std_string stringbard5;
std_string stringbard6;
std_string stringcleric3;
std_string stringcleric_oracle0;
std_string stringcleric_oracle1;
std_string stringcleric_oracle2;
std_string stringcleric_oracle3;
std_string stringcleric_oracle4;
std_string stringcleric_oracle5;
std_string stringcleric_oracle6;
std_string stringcleric_oracle7;
std_string stringcleric_oracle8;
std_string stringcleric_oracle9;
std_string stringdruid0;
std_string stringdruid1;
std_string stringdruid2;
std_string stringdruid3;
std_string stringdruid4;
std_string stringdruid5;
std_string stringdruid6;
std_string stringdruid7;
std_string stringdruid8;
std_string stringdruid9;
std_string stringinquisitor0;
std_string stringinquisitor1;
std_string stringinquisitor2;
std_string stringinquisitor3;
std_string stringinquisitor4;
std_string stringinquisitor5;
std_string stringinquisitor6;
std_string stringmagus0;
std_string stringmagus1;
std_string stringmagus2;
std_string stringmagus3;
std_string stringmagus4;
std_string stringmagus5;
std_string stringmagus6;
std_string stringoracle1;
std_string stringoracle2;
std_string stringoracle3;
std_string stringoracle4;
std_string stringoracle5;
std_string stringoracle8;
std_string stringpaladin1;
std_string stringpaladin2;
std_string stringpaladin3;
std_string stringpaladin4;
std_string stringranger1;
std_string stringranger2;
std_string stringranger3;
std_string stringranger4;
std_string stringsorcerer_wizard0;
std_string stringsorcerer_wizard1;
std_string stringsorcerer_wizard2;
std_string stringsorcerer_wizard3;
std_string stringsorcerer_wizard4;
std_string stringsorcerer_wizard5;
std_string stringsorcerer_wizard6;
std_string stringsorcerer_wizard7;
std_string stringsorcerer_wizard8;
std_string stringsorcerer_wizard9;
std_string stringsummoner0;
std_string stringsummoner1;
std_string stringsummoner2;
std_string stringsummoner3;
std_string stringsummoner4;
std_string stringsummoner5;
std_string stringsummoner6;
std_string stringwitch0;
std_string stringwitch1;
std_string stringwitch2;
std_string stringwitch3;
std_string stringwitch4;
std_string stringwitch5;
std_string stringwitch6;
std_string stringwitch7;
std_string stringwitch8;
std_string stringwitch9;
std_string stringwizard2;
std_string stringwizard4;
std_string stringwizard6;

typedef std::map<std_string, std::ofstream*> FileNameMap;
typedef std::map<std_string, std::ofstream*>::iterator FileNameMapIterator;
FileNameMap GlobalFilenameMap;

/////////////////////////////////////////////////////////////////////////////

void initFileNameMap()
{
	stringRed_Mantis_Assassin2 = "Red_Mantis_Assassin2";
	stringadept0 = "adept0";
	stringadept1 = "adept1";
	stringadept2 = "adept2";
	stringadept3 = "adept3";
	stringalchemist1 = "alchemist1";
	stringalchemist2 = "alchemist2";
	stringalchemist3 = "alchemist3";
	stringalchemist4 = "alchemist4";
	stringalchemist5 = "alchemist5";
	stringalchemist6 = "alchemist6";
	stringantipaladin1 = "antipaladin1";
	stringantipaladin2 = "antipaladin2";
	stringantipaladin3 = "antipaladin3";
	stringantipaladin4 = "antipaladin4";
	stringbard0 = "bard0";
	stringbard1 = "bard1";
	stringbard2 = "bard2";
	stringbard3 = "bard3";
	stringbard4 = "bard4";
	stringbard5 = "bard5";
	stringbard6 = "bard6";
	stringcleric3 = "cleric3";
	stringcleric_oracle0 = "cleric_oracle0";
	stringcleric_oracle1 = "cleric_oracle1";
	stringcleric_oracle2 = "cleric_oracle2";
	stringcleric_oracle3 = "cleric_oracle3";
	stringcleric_oracle4 = "cleric_oracle4";
	stringcleric_oracle5 = "cleric_oracle5";
	stringcleric_oracle6 = "cleric_oracle6";
	stringcleric_oracle7 = "cleric_oracle7";
	stringcleric_oracle8 = "cleric_oracle8";
	stringcleric_oracle9 = "cleric_oracle9";
	stringdruid0 = "druid0";
	stringdruid1 = "druid1";
	stringdruid2 = "druid2";
	stringdruid3 = "druid3";
	stringdruid4 = "druid4";
	stringdruid5 = "druid5";
	stringdruid6 = "druid6";
	stringdruid7 = "druid7";
	stringdruid8 = "druid8";
	stringdruid9 = "druid9";
	stringinquisitor0 = "inquisitor0";
	stringinquisitor1 = "inquisitor1";
	stringinquisitor2 = "inquisitor2";
	stringinquisitor3 = "inquisitor3";
	stringinquisitor4 = "inquisitor4";
	stringinquisitor5 = "inquisitor5";
	stringinquisitor6 = "inquisitor6";
	stringmagus0 = "magus0";
	stringmagus1 = "magus1";
	stringmagus2 = "magus2";
	stringmagus3 = "magus3";
	stringmagus4 = "magus4";
	stringmagus5 = "magus5";
	stringmagus6 = "magus6";
	stringoracle1 = "oracle1";
	stringoracle2 = "oracle2";
	stringoracle3 = "oracle3";
	stringoracle4 = "oracle4";
	stringoracle5 = "oracle5";
	stringoracle8 = "oracle8";
	stringpaladin1 = "paladin1";
	stringpaladin2 = "paladin2";
	stringpaladin3 = "paladin3";
	stringpaladin4 = "paladin4";
	stringranger1 = "ranger1";
	stringranger2 = "ranger2";
	stringranger3 = "ranger3";
	stringranger4 = "ranger4";
	stringsorcerer_wizard0 = "sorcerer_wizard0";
	stringsorcerer_wizard1 = "sorcerer_wizard1";
	stringsorcerer_wizard2 = "sorcerer_wizard2";
	stringsorcerer_wizard3 = "sorcerer_wizard3";
	stringsorcerer_wizard4 = "sorcerer_wizard4";
	stringsorcerer_wizard5 = "sorcerer_wizard5";
	stringsorcerer_wizard6 = "sorcerer_wizard6";
	stringsorcerer_wizard7 = "sorcerer_wizard7";
	stringsorcerer_wizard8 = "sorcerer_wizard8";
	stringsorcerer_wizard9 = "sorcerer_wizard9";
	stringsummoner0 = "summoner0";
	stringsummoner1 = "summoner1";
	stringsummoner2 = "summoner2";
	stringsummoner3 = "summoner3";
	stringsummoner4 = "summoner4";
	stringsummoner5 = "summoner5";
	stringsummoner6 = "summoner6";
	stringwitch0 = "witch0";
	stringwitch1 = "witch1";
	stringwitch2 = "witch2";
	stringwitch3 = "witch3";
	stringwitch4 = "witch4";
	stringwitch5 = "witch5";
	stringwitch6 = "witch6";
	stringwitch7 = "witch7";
	stringwitch8 = "witch8";
	stringwitch9 = "witch9";
	stringwizard2 = "wizard2";
	stringwizard4 = "wizard4";
	stringwizard6 = "wizard6";

	GlobalFilenameMap[stringRed_Mantis_Assassin2] = &streamRed_Mantis_Assassin2;
	GlobalFilenameMap[stringadept0] = &streamadept0;
	GlobalFilenameMap[stringadept1] = &streamadept1;
	GlobalFilenameMap[stringadept2] = &streamadept2;
	GlobalFilenameMap[stringadept3] = &streamadept3;
	GlobalFilenameMap[stringalchemist1] = &streamalchemist1;
	GlobalFilenameMap[stringalchemist2] = &streamalchemist2;
	GlobalFilenameMap[stringalchemist3] = &streamalchemist3;
	GlobalFilenameMap[stringalchemist4] = &streamalchemist4;
	GlobalFilenameMap[stringalchemist5] = &streamalchemist5;
	GlobalFilenameMap[stringalchemist6] = &streamalchemist6;
	GlobalFilenameMap[stringantipaladin1] = &streamantipaladin1;
	GlobalFilenameMap[stringantipaladin2] = &streamantipaladin2;
	GlobalFilenameMap[stringantipaladin3] = &streamantipaladin3;
	GlobalFilenameMap[stringantipaladin4] = &streamantipaladin4;
	GlobalFilenameMap[stringbard0] = &streambard0;
	GlobalFilenameMap[stringbard1] = &streambard1;
	GlobalFilenameMap[stringbard2] = &streambard2;
	GlobalFilenameMap[stringbard3] = &streambard3;
	GlobalFilenameMap[stringbard4] = &streambard4;
	GlobalFilenameMap[stringbard5] = &streambard5;
	GlobalFilenameMap[stringbard6] = &streambard6;
	GlobalFilenameMap[stringcleric3] = &streamcleric3;
	GlobalFilenameMap[stringcleric_oracle0] = &streamcleric_oracle0;
	GlobalFilenameMap[stringcleric_oracle1] = &streamcleric_oracle1;
	GlobalFilenameMap[stringcleric_oracle2] = &streamcleric_oracle2;
	GlobalFilenameMap[stringcleric_oracle3] = &streamcleric_oracle3;
	GlobalFilenameMap[stringcleric_oracle4] = &streamcleric_oracle4;
	GlobalFilenameMap[stringcleric_oracle5] = &streamcleric_oracle5;
	GlobalFilenameMap[stringcleric_oracle6] = &streamcleric_oracle6;
	GlobalFilenameMap[stringcleric_oracle7] = &streamcleric_oracle7;
	GlobalFilenameMap[stringcleric_oracle8] = &streamcleric_oracle8;
	GlobalFilenameMap[stringcleric_oracle9] = &streamcleric_oracle9;
	GlobalFilenameMap[stringdruid0] = &streamdruid0;
	GlobalFilenameMap[stringdruid1] = &streamdruid1;
	GlobalFilenameMap[stringdruid2] = &streamdruid2;
	GlobalFilenameMap[stringdruid3] = &streamdruid3;
	GlobalFilenameMap[stringdruid4] = &streamdruid4;
	GlobalFilenameMap[stringdruid5] = &streamdruid5;
	GlobalFilenameMap[stringdruid6] = &streamdruid6;
	GlobalFilenameMap[stringdruid7] = &streamdruid7;
	GlobalFilenameMap[stringdruid8] = &streamdruid8;
	GlobalFilenameMap[stringdruid9] = &streamdruid9;
	GlobalFilenameMap[stringinquisitor0] = &streaminquisitor0;
	GlobalFilenameMap[stringinquisitor1] = &streaminquisitor1;
	GlobalFilenameMap[stringinquisitor2] = &streaminquisitor2;
	GlobalFilenameMap[stringinquisitor3] = &streaminquisitor3;
	GlobalFilenameMap[stringinquisitor4] = &streaminquisitor4;
	GlobalFilenameMap[stringinquisitor5] = &streaminquisitor5;
	GlobalFilenameMap[stringinquisitor6] = &streaminquisitor6;
	GlobalFilenameMap[stringmagus0] = &streammagus0;
	GlobalFilenameMap[stringmagus1] = &streammagus1;
	GlobalFilenameMap[stringmagus2] = &streammagus2;
	GlobalFilenameMap[stringmagus3] = &streammagus3;
	GlobalFilenameMap[stringmagus4] = &streammagus4;
	GlobalFilenameMap[stringmagus5] = &streammagus5;
	GlobalFilenameMap[stringmagus6] = &streammagus6;
	GlobalFilenameMap[stringoracle1] = &streamoracle1;
	GlobalFilenameMap[stringoracle2] = &streamoracle2;
	GlobalFilenameMap[stringoracle3] = &streamoracle3;
	GlobalFilenameMap[stringoracle4] = &streamoracle4;
	GlobalFilenameMap[stringoracle5] = &streamoracle5;
	GlobalFilenameMap[stringoracle8] = &streamoracle8;
	GlobalFilenameMap[stringpaladin1] = &streampaladin1;
	GlobalFilenameMap[stringpaladin2] = &streampaladin2;
	GlobalFilenameMap[stringpaladin3] = &streampaladin3;
	GlobalFilenameMap[stringpaladin4] = &streampaladin4;
	GlobalFilenameMap[stringranger1] = &streamranger1;
	GlobalFilenameMap[stringranger2] = &streamranger2;
	GlobalFilenameMap[stringranger3] = &streamranger3;
	GlobalFilenameMap[stringranger4] = &streamranger4;
	GlobalFilenameMap[stringsorcerer_wizard0] = &streamsorcerer_wizard0;
	GlobalFilenameMap[stringsorcerer_wizard1] = &streamsorcerer_wizard1;
	GlobalFilenameMap[stringsorcerer_wizard2] = &streamsorcerer_wizard2;
	GlobalFilenameMap[stringsorcerer_wizard3] = &streamsorcerer_wizard3;
	GlobalFilenameMap[stringsorcerer_wizard4] = &streamsorcerer_wizard4;
	GlobalFilenameMap[stringsorcerer_wizard5] = &streamsorcerer_wizard5;
	GlobalFilenameMap[stringsorcerer_wizard6] = &streamsorcerer_wizard6;
	GlobalFilenameMap[stringsorcerer_wizard7] = &streamsorcerer_wizard7;
	GlobalFilenameMap[stringsorcerer_wizard8] = &streamsorcerer_wizard8;
	GlobalFilenameMap[stringsorcerer_wizard9] = &streamsorcerer_wizard9;
	GlobalFilenameMap[stringsummoner0] = &streamsummoner0;
	GlobalFilenameMap[stringsummoner1] = &streamsummoner1;
	GlobalFilenameMap[stringsummoner2] = &streamsummoner2;
	GlobalFilenameMap[stringsummoner3] = &streamsummoner3;
	GlobalFilenameMap[stringsummoner4] = &streamsummoner4;
	GlobalFilenameMap[stringsummoner5] = &streamsummoner5;
	GlobalFilenameMap[stringsummoner6] = &streamsummoner6;
	GlobalFilenameMap[stringwitch0] = &streamwitch0;
	GlobalFilenameMap[stringwitch1] = &streamwitch1;
	GlobalFilenameMap[stringwitch2] = &streamwitch2;
	GlobalFilenameMap[stringwitch3] = &streamwitch3;
	GlobalFilenameMap[stringwitch4] = &streamwitch4;
	GlobalFilenameMap[stringwitch5] = &streamwitch5;
	GlobalFilenameMap[stringwitch6] = &streamwitch6;
	GlobalFilenameMap[stringwitch7] = &streamwitch7;
	GlobalFilenameMap[stringwitch8] = &streamwitch8;
	GlobalFilenameMap[stringwitch9] = &streamwitch9;
	GlobalFilenameMap[stringwizard2] = &streamwizard2;
	GlobalFilenameMap[stringwizard4] = &streamwizard4;
	GlobalFilenameMap[stringwizard6] = &streamwizard6;
}




BOOST_AUTO_TEST_SUITE( Master_suite )

BOOST_AUTO_TEST_CASE(testBoost)
{
	BOOST_CHECK_EQUAL(0, 0.000000000);
}

BOOST_AUTO_TEST_CASE(testOpenFiles)
{
	initFileNameMap();
	unsigned int unNumFiles = GlobalFilenameMap.size();
	BOOST_CHECK_EQUAL(101, unNumFiles);
//	FileNameMapIterator fnmi = GlobalFilenameMap.begin();
//	BOOST_CHECK_EQUAL(fnmi->first, stringRed_Mantis_Assassin2);
//	BOOST_CHECK_EQUAL(fnmi->second, &streamRed_Mantis_Assassin2);
//	std::ofstream &fnm = *(fnmi->second);
//	BOOST_CHECK_EQUAL(fnm, streamRed_Mantis_Assassin2);

	for(FileNameMapIterator fnmi = GlobalFilenameMap.begin(); fnmi != GlobalFilenameMap.end(); fnmi++)
	{
		std_string sFilename = "C:\\Users\\Brian\\Documents\\GitHub\\treasure-source\\CsvParser\\DataFiles\\";
		sFilename += fnmi->first;
		sFilename += ".dat";
		std::ofstream &fnm = *(fnmi->second);
		fnm.open(sFilename, std::ios::out);
	}
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
		std::ofstream FileNames;
		FileNames.open("C:\\Users\\Brian\\Documents\\GitHub\\treasure-source\\CsvParser\\DataFiles\\spell_full.txt", std::ios::out) ;

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
				FileNames << sNextPart << '\n';
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

				std_string sLastPart = sCurrentLine.substr(unTabPos + 1);
				CSTokenizer aTokenizer(sLastPart, ",");
				unsigned int unTokenCount = aTokenizer.NumTokens();
				for(unsigned int unToken = 0; unToken < unTokenCount; ++unToken)
				{
					std_string sNextPart = aTokenizer.NextToken();
					StripLeadingWhiteSpace(sNextPart);
					std_string sLine = sFirstPart + sNextPart;
					outFile << sLine << '\n';
					FileNames << sNextPart << '\n';
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

BOOST_AUTO_TEST_CASE(testWriteFiles)
{
	bool bTestFileWorks = true;
	try
	{
		CTextFile aFile("C:\\Users\\Brian\\Documents\\GitHub\\treasure-source\\CsvParser\\DataFiles\\spell_full.dat");
/*
		std_string sCurrentLine = aFile.NextLine();
		//BOOST_CHECK_EQUAL(sCurrentLine, "foo");

		unsigned int unTabPos = sCurrentLine.rfind('	');
		if(std_string::npos != unTabPos)
		{
			std_string sFirstPart = sCurrentLine.substr(0, unTabPos + 1);
			BOOST_CHECK_EQUAL(sFirstPart,"	9999	9999	Acid Arrow	");

			std_string sLastPart = sCurrentLine.substr(unTabPos + 1);
//			BOOST_CHECK_EQUAL(sLastPart, "sorcerer/wizard 2, magus 2");
			CSTokenizer aTokenizer(sLastPart, ",");
			unsigned int unTokenCount = aTokenizer.NumTokens();
			BOOST_CHECK_EQUAL(1, unTokenCount);
			std_string sNextPart = aTokenizer.NextToken();
			StripLeadingWhiteSpace(sNextPart);
			BOOST_CHECK_EQUAL(sNextPart, "sorcerer_wizard2");

			std::ofstream &fnm = *(GlobalFilenameMap[sNextPart]);
			BOOST_CHECK_EQUAL(fnm, &streamsorcerer_wizard2);
			fnm << sFirstPart << "\n";
		}
*/
		while (!aFile.IsAtEndOfFile())
		{
			std_string sCurrentLine = aFile.NextLine();
			//BOOST_CHECK_EQUAL(sCurrentLine, "foo");

			unsigned int unTabPos = sCurrentLine.rfind('	');
			if(std_string::npos != unTabPos)
			{
				std_string sFirstPart = sCurrentLine.substr(0, unTabPos + 1);
//				BOOST_CHECK_EQUAL(sFirstPart,"	9999	9999	Acid Arrow	");

				std_string sLastPart = sCurrentLine.substr(unTabPos + 1);
	//			BOOST_CHECK_EQUAL(sLastPart, "sorcerer/wizard 2, magus 2");
				CSTokenizer aTokenizer(sLastPart, ",");
				unsigned int unTokenCount = aTokenizer.NumTokens();
//				BOOST_CHECK_EQUAL(1, unTokenCount);
				std_string sNextPart = aTokenizer.NextToken();
				StripLeadingWhiteSpace(sNextPart);
//				BOOST_CHECK_EQUAL(sNextPart, "sorcerer_wizard2");

				std::ofstream &fnm = *(GlobalFilenameMap[sNextPart]);
//				BOOST_CHECK_EQUAL(fnm, &streamsorcerer_wizard2);
				fnm << sFirstPart << "\n";
			}
		}

	}
	catch(...)
	{
		bTestFileWorks = false;
	}
	BOOST_CHECK_EQUAL(bTestFileWorks, true);

}

BOOST_AUTO_TEST_CASE(testCloseFiles)
{
	unsigned int unNumFiles = GlobalFilenameMap.size();
	BOOST_CHECK_EQUAL(101, unNumFiles);

	for(FileNameMapIterator fnmi = GlobalFilenameMap.begin(); fnmi != GlobalFilenameMap.end(); fnmi++)
	{
		std::ofstream &fnm = *(fnmi->second);
		fnm.close();
	}
}

BOOST_AUTO_TEST_SUITE_END()
