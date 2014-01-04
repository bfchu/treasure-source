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

typedef std::map<std_string, std::ofstream*> FileNameMap;
typedef std::map<std_string, std::ofstream*>::iterator FileNameMapIterator;
FileNameMap GlobalFilenameMap;

/////////////////////////////////////////////////////////////////////////////

void initFileNameMap()
{
	GlobalFilenameMap["Red_Mantis_Assassin2"] = &streamRed_Mantis_Assassin2;
	GlobalFilenameMap["adept0"] = &streamadept0;
	GlobalFilenameMap["adept1"] = &streamadept1;
	GlobalFilenameMap["adept2"] = &streamadept2;
	GlobalFilenameMap["adept3"] = &streamadept3;
	GlobalFilenameMap["alchemist1"] = &streamalchemist1;
	GlobalFilenameMap["alchemist2"] = &streamalchemist2;
	GlobalFilenameMap["alchemist3"] = &streamalchemist3;
	GlobalFilenameMap["alchemist4"] = &streamalchemist4;
	GlobalFilenameMap["alchemist5"] = &streamalchemist5;
	GlobalFilenameMap["alchemist6"] = &streamalchemist6;
	GlobalFilenameMap["antipaladin1"] = &streamantipaladin1;
	GlobalFilenameMap["antipaladin2"] = &streamantipaladin2;
	GlobalFilenameMap["antipaladin3"] = &streamantipaladin3;
	GlobalFilenameMap["antipaladin4"] = &streamantipaladin4;
	GlobalFilenameMap["bard0"] = &streambard0;
	GlobalFilenameMap["bard1"] = &streambard1;
	GlobalFilenameMap["bard2"] = &streambard2;
	GlobalFilenameMap["bard3"] = &streambard3;
	GlobalFilenameMap["bard4"] = &streambard4;
	GlobalFilenameMap["bard5"] = &streambard5;
	GlobalFilenameMap["bard6"] = &streambard6;
	GlobalFilenameMap["cleric3"] = &streamcleric3;
	GlobalFilenameMap["cleric_oracle0"] = &streamcleric_oracle0;
	GlobalFilenameMap["cleric_oracle1"] = &streamcleric_oracle1;
	GlobalFilenameMap["cleric_oracle2"] = &streamcleric_oracle2;
	GlobalFilenameMap["cleric_oracle3"] = &streamcleric_oracle3;
	GlobalFilenameMap["cleric_oracle4"] = &streamcleric_oracle4;
	GlobalFilenameMap["cleric_oracle5"] = &streamcleric_oracle5;
	GlobalFilenameMap["cleric_oracle6"] = &streamcleric_oracle6;
	GlobalFilenameMap["cleric_oracle7"] = &streamcleric_oracle7;
	GlobalFilenameMap["cleric_oracle8"] = &streamcleric_oracle8;
	GlobalFilenameMap["cleric_oracle9"] = &streamcleric_oracle9;
	GlobalFilenameMap["druid0"] = &streamdruid0;
	GlobalFilenameMap["druid1"] = &streamdruid1;
	GlobalFilenameMap["druid2"] = &streamdruid2;
	GlobalFilenameMap["druid3"] = &streamdruid3;
	GlobalFilenameMap["druid4"] = &streamdruid4;
	GlobalFilenameMap["druid5"] = &streamdruid5;
	GlobalFilenameMap["druid6"] = &streamdruid6;
	GlobalFilenameMap["druid7"] = &streamdruid7;
	GlobalFilenameMap["druid8"] = &streamdruid8;
	GlobalFilenameMap["druid9"] = &streamdruid9;
	GlobalFilenameMap["inquisitor0"] = &streaminquisitor0;
	GlobalFilenameMap["inquisitor1"] = &streaminquisitor1;
	GlobalFilenameMap["inquisitor2"] = &streaminquisitor2;
	GlobalFilenameMap["inquisitor3"] = &streaminquisitor3;
	GlobalFilenameMap["inquisitor4"] = &streaminquisitor4;
	GlobalFilenameMap["inquisitor5"] = &streaminquisitor5;
	GlobalFilenameMap["inquisitor6"] = &streaminquisitor6;
	GlobalFilenameMap["magus0"] = &streammagus0;
	GlobalFilenameMap["magus1"] = &streammagus1;
	GlobalFilenameMap["magus2"] = &streammagus2;
	GlobalFilenameMap["magus3"] = &streammagus3;
	GlobalFilenameMap["magus4"] = &streammagus4;
	GlobalFilenameMap["magus5"] = &streammagus5;
	GlobalFilenameMap["magus6"] = &streammagus6;
	GlobalFilenameMap["oracle1"] = &streamoracle1;
	GlobalFilenameMap["oracle2"] = &streamoracle2;
	GlobalFilenameMap["oracle3"] = &streamoracle3;
	GlobalFilenameMap["oracle4"] = &streamoracle4;
	GlobalFilenameMap["oracle5"] = &streamoracle5;
	GlobalFilenameMap["oracle8"] = &streamoracle8;
	GlobalFilenameMap["paladin1"] = &streampaladin1;
	GlobalFilenameMap["paladin2"] = &streampaladin2;
	GlobalFilenameMap["paladin3"] = &streampaladin3;
	GlobalFilenameMap["paladin4"] = &streampaladin4;
	GlobalFilenameMap["ranger1"] = &streamranger1;
	GlobalFilenameMap["ranger2"] = &streamranger2;
	GlobalFilenameMap["ranger3"] = &streamranger3;
	GlobalFilenameMap["ranger4"] = &streamranger4;
	GlobalFilenameMap["sorcerer_wizard0"] = &streamsorcerer_wizard0;
	GlobalFilenameMap["sorcerer_wizard1"] = &streamsorcerer_wizard1;
	GlobalFilenameMap["sorcerer_wizard2"] = &streamsorcerer_wizard2;
	GlobalFilenameMap["sorcerer_wizard3"] = &streamsorcerer_wizard3;
	GlobalFilenameMap["sorcerer_wizard4"] = &streamsorcerer_wizard4;
	GlobalFilenameMap["sorcerer_wizard5"] = &streamsorcerer_wizard5;
	GlobalFilenameMap["sorcerer_wizard6"] = &streamsorcerer_wizard6;
	GlobalFilenameMap["sorcerer_wizard7"] = &streamsorcerer_wizard7;
	GlobalFilenameMap["sorcerer_wizard8"] = &streamsorcerer_wizard8;
	GlobalFilenameMap["sorcerer_wizard9"] = &streamsorcerer_wizard9;
	GlobalFilenameMap["summoner0"] = &streamsummoner0;
	GlobalFilenameMap["summoner1"] = &streamsummoner1;
	GlobalFilenameMap["summoner2"] = &streamsummoner2;
	GlobalFilenameMap["summoner3"] = &streamsummoner3;
	GlobalFilenameMap["summoner4"] = &streamsummoner4;
	GlobalFilenameMap["summoner5"] = &streamsummoner5;
	GlobalFilenameMap["summoner6"] = &streamsummoner6;
	GlobalFilenameMap["witch0"] = &streamwitch0;
	GlobalFilenameMap["witch1"] = &streamwitch1;
	GlobalFilenameMap["witch2"] = &streamwitch2;
	GlobalFilenameMap["witch3"] = &streamwitch3;
	GlobalFilenameMap["witch4"] = &streamwitch4;
	GlobalFilenameMap["witch5"] = &streamwitch5;
	GlobalFilenameMap["witch6"] = &streamwitch6;
	GlobalFilenameMap["witch7"] = &streamwitch7;
	GlobalFilenameMap["witch8"] = &streamwitch8;
	GlobalFilenameMap["witch9"] = &streamwitch9;
	GlobalFilenameMap["wizard2"] = &streamwizard2;
	GlobalFilenameMap["wizard4"] = &streamwizard4;
	GlobalFilenameMap["wizard6"] = &streamwizard6;
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
