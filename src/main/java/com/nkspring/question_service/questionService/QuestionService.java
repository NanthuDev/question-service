package com.nkspring.question_service.questionService;



import com.nkspring.question_service.dao.QuestionDao;
import com.nkspring.question_service.model.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity< List<Questions>> getAllQuestions() {
        try{
            return new ResponseEntity<>( questionDao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public List<Questions> getQuestionsByCategory(String category){
        return questionDao.findByCategory(category);
    }

    public ResponseEntity<String> addQuestion(Questions question){
         questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED) ;
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String catName, Integer numOfQuestions) {
        List<Integer> questions = questionDao.findRandomQuestionByCategory(catName,numOfQuestions);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }
}
