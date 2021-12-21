using System.Linq;

namespace FindRankedStudents
{
    public class Student
    {
        private static Student[] _AllStudents;
        public int StudentNumber { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public float average { get; private set; }
        private int _howManyCoursesPassed = 0;

        public static Student[] GetRankedStudents()
        {
            return new[] {_AllStudents[0], _AllStudents[1], _AllStudents[2]};
        }

        public static Student GetStudentById(int id)
        {
            return _AllStudents.FirstOrDefault(student => student.StudentNumber == id);
        }

        public static void UpdateListOfStudents(Student[] newList)
        {
            _AllStudents = newList;
        }

        public static void SortStudentsByAverage()
        {
            _AllStudents = _AllStudents.OrderByDescending(student => student.average).ToArray();
        }

        public void SubmitScore(float newScore)
        {
            average = (average * _howManyCoursesPassed + newScore) / ++_howManyCoursesPassed;
        }
    }
}