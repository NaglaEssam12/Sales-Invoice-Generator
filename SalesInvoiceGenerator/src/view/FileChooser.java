package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileChooser extends JFrame implements ActionListener {
        JMenuBar mb;
        JMenu file;
        JMenuItem load;
        JMenuItem save;
        JTextArea ta;
        FileChooser(){
            load=new JMenuItem("Load File");
            load.addActionListener(this);
            save=new JMenuItem("Save File");
            save.addActionListener(this);
            file=new JMenu("File");
            file.add(load);
            file.add(save);
            mb=new JMenuBar();
            mb.setBounds(0,0,800,20);
            mb.add(file);
            ta=new JTextArea(800,800);
            ta.setBounds(0,20,800,800);
            add(mb);
            add(ta);
        }
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==load){
                JFileChooser fc=new JFileChooser();
                int i=fc.showOpenDialog(this);
                if(i==JFileChooser.APPROVE_OPTION){
                    File f=fc.getSelectedFile();
                    String filepath=f.getPath();
                    try{
                        BufferedReader br=new BufferedReader(new FileReader(filepath));
                        String s1="",s2="";
                        while((s1=br.readLine())!=null){
                            s2+=s1+"\n";
                        }
                        ta.setText(s2);
                        br.close();
                    }catch (Exception ex) {ex.printStackTrace();  }
                }
            }
        }
    }
