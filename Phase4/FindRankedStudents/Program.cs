using System;
using System.Text.Json;


namespace FindRankedStudents
{
    class Program
    {
        static void Main(string[] args)
        {
            var listOfStudents = GetObjectsFromJsonFile<Student>("../../../Students.json");
            Student.UpdateListOfStudents(listOfStudents);
            var listOfScores = GetObjectsFromJsonFile<ExamOutcome>("../../../Scores.json");
            foreach (var score in listOfScores)
            {
                score.ApplyScore();
            }
            Student.SortStudentsByAverage();
            foreach (var student in Student.GetRankedStudents())
            {
                Console.WriteLine( student.FirstName + " "
                                  + student.LastName + " : "
                                  + student.average);
            }
        }

        private static T[] GetObjectsFromJsonFile<T>(string address)
        {
            string json = System.IO.File.ReadAllText(address);
            return JsonSerializer.Deserialize<T[]>(json);
        }
    }
}