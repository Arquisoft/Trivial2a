package es.uniovi.asw.trivial.bussines;

import java.util.List;

public interface StatisticsLoader {

	// Global Statistics

	int getGlobalCorrectAnswers();

	int getGlobalWrongAnwers();

	int getGlobalCorrectSportsAnswers();

	int getGlobalCorrectGeographyAnswers();

	int getGlobalCorrectShowsAndEntertainmentAnswers();

	int getGlobalCorrectHistoryAnswers();

	int getGlobalCorrectArtAndLiteratureAnswers();

	int getGlobalCorrectScienceAndTechnologyAnswers();

	// IndividualStatistics
	List<Object[]> getInfoQuestionsByCategory(String category);

}
