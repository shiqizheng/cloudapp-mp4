import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;



import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class FileReaderSpout implements IRichSpout {
  private SpoutOutputCollector _collector;
  private TopologyContext context;
  private FileReader fileReader;

  @Override
  public void open(Map conf, TopologyContext context,
                   SpoutOutputCollector collector) {
        //String file= "/cloudapp-mp3/data.txt";
        //BufferedReader buffIn=new BufferedReader(new java.io.FileReader(file));
      try{
                this.context=context;
                this.fileReader=new FileReader(conf.get("inputFile").toString());

        }catch(FileNotFoundException e){
                throw new RuntimeException("Error reading file" + conf.get("inputFile"));
        }



/*
    ----------------------TODO-----------------------
   Task: initialize the file reader
        file= "/cloudapp-mp3/data.txt"
        BufferedReader buffIn=new BufferedReader(new FileReader(file));

    ------------------------------------------------- */


                this._collector = collector;
    }

  @Override
  public void nextTuple() {

         Utils.sleep(100);
         BufferedReader buffIn = new BufferedReader(fileReader);
        String line;
        try{
                while((line=buffIn.readLine())!=null){


          _collector.emit(new Values(line),line);
                }
        }catch(Exception e){
                throw new RuntimeException("Error reading typle", e);
        }

     /*
    ----------------------TODO-----------------------
    Task:
    1. read the next line and emit a tuple for it
    2. don't forget to sleep when the file is entirely read to prevent a busy-loop

    ------------------------------------------------- */


  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {

    declarer.declare(new Fields("word"));

  }

  @Override
  public void close() {
   /*
    ----------------------TODO-----------------------
    Task: close the file


    ------------------------------------------------- */
     try{
        fileReader.close();
     }catch(IOException e){
        e.printStackTrace();
     }
  }

  @Override
  public void activate() {
  }

  @Override
  public void deactivate() {
  }

  @Override
  public void ack(Object msgId) {
  }

  @Override
  public void fail(Object msgId) {
  }

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return null;
  }
}

