package sample;

public class StudentRecord {
    private String sid;
    private float assignments;
    private float midterm;
    private float finalExam;
    private double finalMark;
    private char letterGrade;



    public StudentRecord() { }

    public StudentRecord(String sid, float assignments, float midterm, float finalExam) {
        this.sid = sid;
        this.assignments = assignments;
        this.midterm = midterm;
        this.finalExam = finalExam;
        finalMark = (this.assignments * 0.2) + (this.midterm * 0.3) + (this.finalExam * 0.5);

        if(finalMark >= 80 && finalMark <= 100){
            letterGrade = 'A';
        }
        else if(finalMark >= 70 && finalMark <= 79){
            letterGrade = 'B';
        }
        else if(finalMark >= 60 && finalMark <= 69){
            letterGrade = 'C';
        }
        else if(finalMark >= 50 && finalMark <= 59){
            letterGrade = 'D';
        }
        else{
            letterGrade = 'F';
        }

    }

    public String getSid() { return this.sid; }

    public float getAssignments() { return this.assignments; }

    public float getMidterm() { return this.midterm; }

    public float getFinalExam() { return this.finalExam; }

    public double getFinalMark() { return this.finalMark;}

    public char getLetterGrade(){ return this.letterGrade;}


}