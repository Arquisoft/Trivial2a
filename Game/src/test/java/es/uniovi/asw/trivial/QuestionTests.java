package es.uniovi.asw.trivial;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.trivial.factories.PersistenceFactory;
import es.uniovi.asw.trivial.model.Category;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.persistence.QuestionDao;
import es.uniovi.asw.trivial.persistence.UserDao;

public class QuestionTests {

	private QuestionDao questionDao;

	
	 @Before
	 public void setUp() {
	 questionDao = PersistenceFactory.persistenceFactory()
	 .createQuestionDao();
	 // Nos aseguramos de que la base de las preguntas está vacía
	 questionDao.deleteAll();
	 }

	@Test
	public void dataEmptyTest() {
		questionDao = PersistenceFactory.persistenceFactory()
				.createQuestionDao();
		questionDao.deleteAll();
		assertThat(questionDao.getQuestions().size()).isEqualTo(0);
	}

	 @Test
	 public void dataAddTest() {
	
	 questionDao = PersistenceFactory.persistenceFactory()
	 .createQuestionDao();
	 Question quest = new Question();
	 quest.setStatement("¿Funciona bien al incluir esta pregunta?");
	 quest.setCorrectAnswer("Sí! Ivi mola!");
	 quest.setCategory(Category.HISTORY);
	 List<String> resp = new ArrayList<String>();
	 resp.add("nu :(");
	 resp.add("No, lo siento");
	 resp.add("Más quisieras!");
	 quest.setIncorrectAnswers(resp);
	
	 questionDao.save(quest);
	
	 assertThat(questionDao.getQuestions().size()).isEqualTo(1);
	 }
	 
	 @Test
	 public void dataGetCategory() {
	
	 questionDao = PersistenceFactory.persistenceFactory()
	 .createQuestionDao();
	 Question quest = new Question();
	 quest.setStatement("¿Funciona bien al incluir esta pregunta?");
	 quest.setCorrectAnswer("Sí! Ivi mola!");
	 quest.setCategory(Category.HISTORY);
	 List<String> resp = new ArrayList<String>();
	 resp.add("nu :(");
	 resp.add("No, lo siento");
	 resp.add("Más quisieras!");
	 quest.setIncorrectAnswers(resp);
	 questionDao.save(quest);
	 
	 quest = new Question();
	 quest.setStatement("¿OTRA!?");
	 quest.setCorrectAnswer("Sí! Ivi mola!");
	 quest.setCategory(Category.HISTORY);
	 resp = new ArrayList<String>();
	 resp.add("nu :(");
	 resp.add("No, lo siento");
	 resp.add("Más quisieras!");
	 quest.setIncorrectAnswers(resp);
	 questionDao.save(quest);
	 
	 Question questTest = questionDao.getQuestionByCategory(Category.HISTORY);
	
	 assertThat(questTest.getCategory()).isEqualTo(Category.HISTORY);
	 
	 }
}