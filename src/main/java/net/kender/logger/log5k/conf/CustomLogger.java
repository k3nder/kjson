package net.kender.logger.log5k.conf;

public class CustomLogger {
    public String ansicolor_;
    public String TypeMsg_;
    public Boolean dateOn_;
    public Boolean classComplete_;
    public Boolean registerOnFile_;
    public Boolean RegisterOnDates_;
    public Boolean threadOn_;
    public CustomLogger(String ANSIColor,String TypeMsg,Boolean dateOn,Boolean classComplete,Boolean threadOn,Boolean registerOnFile,Boolean RegisterOnDates){
        ansicolor_ = ANSIColor;
        TypeMsg_ = TypeMsg;
        dateOn_ = dateOn;
        classComplete_ = classComplete;
        threadOn_ = threadOn;
        registerOnFile_ = registerOnFile;
        RegisterOnDates_ = RegisterOnDates;
    }
}
