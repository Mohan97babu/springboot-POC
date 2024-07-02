package com.school.test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.test.dto.ResponsePostDTO;
import com.school.test.entity.Choice;
import com.school.test.entity.Question;
import com.school.test.entity.Score;
import com.school.test.entity.Student;
import com.school.test.entity.StudentAnswer;
import com.school.test.entity.Test;
import com.school.test.repository.ChoiceRepository;
import com.school.test.repository.ScoreRepository;
import com.school.test.repository.StudentAnswerRepository;

@Service
public class StudentAnswerService {

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    @Autowired
    private ChoiceRepository choiceRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    public ResponsePostDTO addStudentAnswer(final StudentAnswer studentAnswer) {
        ResponsePostDTO response = new ResponsePostDTO();

        // Check if the student has already answered this question in this test
        Optional<StudentAnswer> existingAnswer = studentAnswerRepository.findByStudentAndQuestionAndTest(
                studentAnswer.getStudent(), studentAnswer.getQuestion(), studentAnswer.getTest());

        if (existingAnswer.isPresent()) {
            response.setMessage("Student has already answered this question.");
            return response;
        }

        Optional<Choice> choiceOptional = choiceRepository.findById(studentAnswer.getChoice().getId());

        if (choiceOptional.isPresent()) 
        {
            Choice existingChoice = choiceOptional.get();
            Question question = existingChoice.getQuestion();

            if (question != null && question.getId() == studentAnswer.getQuestion().getId()) 
            {
                Student student = studentAnswer.getStudent();
                Test test = studentAnswer.getTest();

                Optional<Score> scoreOptional = scoreRepository.findByStudentAndTest(student, test);
                Score score;
                if (scoreOptional.isPresent()) 
                {
                    score = scoreOptional.get();
                } else {
                    score = new Score();
                    score.setStudent(student);
                    score.setTest(test);
                }
                if (existingChoice.getIsCorrect() == 1) 
                {
                    score.setScore(score.getScore() + 1);
                    response.setMessage("Score updated successfully.");
                } else {
                    response.setMessage("The choice is incorrect, score remains the same.");
                }
                scoreRepository.save(score);
                studentAnswerRepository.save(studentAnswer);
                response.setId(studentAnswer.getId());
            } else {
                response.setMessage("The question associated with the choice does not match the question in the student answer.");
            }
        } else {
            response.setMessage("Choice not found.");
        }

        return response;
    }

}

