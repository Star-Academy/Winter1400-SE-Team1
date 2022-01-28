using System;
using System.Linq;
using System.Runtime.Serialization;

namespace FindRankedStudents
{
    [Serializable]
    public class Student : ISerializable
    {
        public Student(String firstName, String lastName,int studentNumber ,float average)
        {
            LastName = lastName;
            FirstName = firstName;
            StudentNumber = studentNumber;
            Average = average;
        }
       
        protected Student(SerializationInfo serializationInfo, StreamingContext streamingContext)
        {
            FirstName = serializationInfo.GetString("FirstName");
            LastName = serializationInfo.GetString("LastName");
            StudentNumber = serializationInfo.GetInt16("StudentNumber");
        }
        private static Student[] _AllStudents;
        public int StudentNumber { get; }
        public string FirstName { get; }
        public string LastName { get; }
        public float Average { get; private set; }
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
            _AllStudents = _AllStudents.OrderByDescending(student => student.Average).ToArray();
        }

        public void SubmitScore(float newScore)
        {
            Average = (Average * _howManyCoursesPassed + newScore) / ++_howManyCoursesPassed;
        }

        public void GetObjectData(SerializationInfo info, StreamingContext context)
        {
            throw new NotImplementedException();
        }
    }
}