import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

//  "C:\\Users\\Munib\\Desktop\\test.txt"
// Integer.parseInt(args[0])
public class ProcSim
{
    public static void main(String[] args)throws Exception
    {
        String path=args[0];
        int algo=Integer.parseInt(args[1]);
        FileReading reading=new FileReading();
        reading.getDataFromFile(path);
        ArrayList<Integer>[] CPU=new ArrayList[reading.cpus];
        int executedProcesses;

        CPU[0]=CPU[1]=null;
        switch (algo){
            case 1:
            {
                System.out.println("Algo: FCFS");
                executedProcesses=0;
                ArrayList<ArrayList<Integer>> readyQue=new ArrayList<>();
                int clock=1;
                Integer[] timeOut=new Integer[CPU.length];
                for(int i=0;i<CPU.length;i++){
                    timeOut[i]=0;
                }
                boolean[] occupied=new boolean[CPU.length];
                int totalWaitingTime=0;
                int totalExecutionTime=0;
                int inReady=0;
                while (executedProcesses!=reading.processCount){
                    for(int i=inReady;i<reading.processCount;i++){
                        if(clock >=reading.processes.get(i).get(1))
                        {
                            readyQue.add(reading.processes.get(i));
                            inReady++;
                        }
                    }
                    for(int i=0;i<CPU.length;i++){
                        if(timeOut[i]==clock){
                            CPU[i]=null;
                            if (occupied[i]){
                                executedProcesses++;
                                occupied[i]=false;
                            }
                        }
                        if(CPU[i]==null&& !readyQue.isEmpty()){
                            CPU[i]=readyQue.get(0);
                            totalExecutionTime=totalExecutionTime+readyQue.get(0).get(2);
                            timeOut[i] =clock+readyQue.get(0).get(2);
                            readyQue.remove(0);
                            occupied[i]=true;
                            continue;
                        }
                    }
                    totalWaitingTime=totalWaitingTime+readyQue.size();
                    clock++;
                }
                int turnAroundTime=totalExecutionTime+totalWaitingTime;
                float ave=turnAroundTime/reading.processCount;
                System.out.println("Total Waiting time: "+totalWaitingTime);
                System.out.println("Average TurnAround time="+ turnAroundTime+"/"+reading.processCount+"="+ave);
                break;
            }


            case 2:
            {
                System.out.println("Algo: SJF");
                executedProcesses=0;
                ArrayList<ArrayList<Integer>> readyQue=new ArrayList<>();
                int clock=1;
                Integer[] timeOut=new Integer[CPU.length];
                for(int i=0;i<CPU.length;i++){
                    timeOut[i]=0;
                }
                boolean[] occupied=new boolean[CPU.length];
                int totalWaitingTime=0;
                int totalExecutionTime=0;
                int inReady=0;
                while (executedProcesses!=reading.processCount){
                    for(int i=inReady;i<reading.processCount;i++){
                        if(clock >=reading.processes.get(i).get(1))
                        {
                            readyQue.add(reading.processes.get(i));
                            inReady++;
                        }
                    }

                    for(int i=0;i<CPU.length;i++){
                        if(timeOut[i]==clock){
                            CPU[i]=null;
                            if (occupied[i]){
                                executedProcesses++;
                                occupied[i]=false;
                            }
                        }
                        int index=0;
                        if(CPU[i]==null&& !readyQue.isEmpty()){
                            int temp=150;
                            for(int j=0;j<readyQue.size();j++){
                                if(readyQue.get(j).get(2)<temp){
                                    temp=readyQue.get(j).get(2);
                                    index=j;
                                }
                            }
                            CPU[i]=readyQue.get(index);
                            totalExecutionTime=totalExecutionTime+readyQue.get(index).get(2);
                            timeOut[i] =clock+readyQue.get(index).get(2);
                            readyQue.remove(index);
                            occupied[i]=true;
                            continue;
                        }
                    }
                    totalWaitingTime=totalWaitingTime+readyQue.size();
                    clock++;
                }
                int turnAroundTime=totalExecutionTime+totalWaitingTime;
                float ave=turnAroundTime/reading.processCount;
                System.out.println("Total Waiting time: "+totalWaitingTime);
                System.out.println("Average TurnAround time="+ turnAroundTime+"/"+reading.processCount+"="+ave);
                break;
            }
            case 3:
            {
                System.out.println("Algo: Priority");
                executedProcesses=0;
                ArrayList<ArrayList<Integer>> readyQue=new ArrayList<>();
                int clock=1;
                Integer[] timeOut=new Integer[CPU.length];
                for(int i=0;i<CPU.length;i++){
                    timeOut[i]=0;
                }
                boolean[] occupied=new boolean[CPU.length];
                int totalWaitingTime=0;
                int totalExecutionTime=0;
                int inReady=0;
                while (executedProcesses!=reading.processCount){
                    for(int i=inReady;i<reading.processCount;i++){
                        if(clock >=reading.processes.get(i).get(1))
                        {
                            readyQue.add(reading.processes.get(i));
                            inReady++;
                        }
                    }

                    for(int i=0;i<CPU.length;i++){
                        if(timeOut[i]==clock){
                            CPU[i]=null;
                            if (occupied[i]){
                                executedProcesses++;
                                occupied[i]=false;
                            }
                        }
                        int index=0;
                        if(CPU[i]==null&& !readyQue.isEmpty()){
                            int temp=150;
                            for(int j=0;j<readyQue.size();j++){
                                if(readyQue.get(j).get(3)<temp){
                                    temp=readyQue.get(j).get(3);
                                    index=j;
                                }
                            }
                            CPU[i]=readyQue.get(index);
                            totalExecutionTime=totalExecutionTime+readyQue.get(index).get(2);
                            timeOut[i] =clock+readyQue.get(index).get(2);
                            readyQue.remove(index);
                            occupied[i]=true;
                            continue;
                        }
                    }
                    totalWaitingTime=totalWaitingTime+readyQue.size();
                    clock++;
                }
                int turnAroundTime=totalExecutionTime+totalWaitingTime;
                float ave=turnAroundTime/reading.processCount;
                System.out.println("Total Waiting time: "+totalWaitingTime);
                System.out.println("Average TurnAround time="+ turnAroundTime+"/"+reading.processCount+"="+ave);
                break;
            }
            default:
            {
                System.out.println("No such algo...");
                break;
            }
        }
    }

}

class FileReading {
    ArrayList<ArrayList<Integer>> processes=new ArrayList<>();
    int cpus;
    int processCount;
    ArrayList<Integer> fileInfo=new ArrayList<>();

    void getDataFromFile(String path) throws IOException {
        File file = new File(path);

        BufferedReader br = new BufferedReader(new FileReader(file));


        String st;

        while ((st = br.readLine()) != null){
            Scanner scanner=new Scanner(st);
            while (scanner.hasNextInt()) {
                fileInfo.add(scanner.nextInt());
            }
        }
        cpus=fileInfo.get(0);
        processCount=fileInfo.get(1);
        fileInfo.remove(0);
        fileInfo.remove(0);

        for(int j=0;j<processCount;j++)
        {
            ArrayList<Integer> process=new ArrayList<>();
            for (int i =0; i <4; i++) {
                process.add(fileInfo.get(0));
                fileInfo.remove(0);
            }
            processes.add(process);
        }
    }

}