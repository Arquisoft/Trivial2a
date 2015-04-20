package persistence;

import java.util.List;

import model.Category;
import model.Question;

/**
 * Persistencia para Question
 * @author ivana
 *
 */

public interface QuestionDao {
	List<Question> getQuestions();
	void save(Question question);
	Question getQuestionByCategory(Category category);
	void deleteAll();
}
