package persistence;

import java.util.Map;

public interface StatisticDao {

	Map<Integer, Integer> getStatisticSports();
	Map<Integer, Integer> getStatisticShows();
	Map<Integer, Integer> getStatisticScience();
	Map<Integer, Integer> getStatisticArt();
	Map<Integer, Integer> getStatisticGeography();
	Map<Integer, Integer> getStatisticHistory();
	
	Map<String,Map<Integer,Integer>> getAllStatistics();
}
