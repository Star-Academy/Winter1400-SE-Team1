namespace FindRankedStudents
{
    public class ExamOutcome
    {
        public int StudentNumber { get; set; }
        public float Score { get; set; }

        public void ApplyScore()
        {
            Student.GetStudentById(StudentNumber).SubmitScore(Score);
        }
    }
}