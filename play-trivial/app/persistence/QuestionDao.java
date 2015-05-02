package persistence;

import java.util.List;

import models.Category;
import models.Question;

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
