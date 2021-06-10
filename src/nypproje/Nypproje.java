package nypproje;
import java.util.*; 
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import javax.swing.border.*;  
import javax.swing.event.*;
import javax.swing.table.*;
import java.text.*;

interface TemelGUI extends ActionListener{
    JFrame frame = new JFrame("Nesnelerin Bankası");
    JTextField girisno = new JTextField();
    JPasswordField girissifre=new JPasswordField();
    JLabel girislabel1=new JLabel("ID Numarası:");
    JLabel girislabel2=new JLabel("Şifre:");
    JLabel girislabel3=new JLabel("Giriş Ekranı");
    JTextArea giristxt = new JTextArea();
    JScrollPane girissp = new JScrollPane(giristxt);
    JButton girisbuton = new JButton("GİRİŞ");
    JButton girisgecis = new JButton("Giriş Yap");
    JButton kayitbuton = new JButton("Kayıt Ol");
    JButton kayitgecis = new JButton("Üyelik Başvurusu");
    JButton testbilgi = new JButton("Test Hesapları");
    JButton info = new JButton("Uygulama Bilgileri");
    JLabel kayitlabel1 = new JLabel("Ad: ");
    JLabel kayitlabel2 = new JLabel("Soyad: ");
    JLabel kayitlabel3 = new JLabel("Şifre: ");
    JLabel kayitlabel4 = new JLabel("Şifre Onay: ");
    JTextField kayitad = new JTextField();
    JTextField kayitsoyad = new JTextField();
    JPasswordField kayitsifre= new JPasswordField();
    JPasswordField kayitsifreonay = new JPasswordField();
    JButton oturumkapat = new JButton("Oturumu Kapat");
    JPanel exit = new JPanel();
    String girisstring1 = "Üye olabilmek için onay gerekmektedir. Bu yüzden bazı hesaplara hızlı erişim için bilgiler şunlardır:\n\n"
            + "ID: 000000001 (Müşteri)\nŞifre: 123456789\n_________________\n"
            + "ID: 000000002 (Müşteri)\nŞifre: 123456789\n_________________\n"
            + "ID: 000000003 (Müşteri)\nŞifre: 123456789\n_________________\n"
            + "ID: 000001 (Yönetici)\nŞifre: 123456789\n_________________\n"
            + "ID: 000002 (Yetkili Personel)\nŞifre: 123456789\n_________________\n"
            + "ID: 000003 (Yetkili Personel)\nŞifre: 123456789\n_________________\n";
    String girisstring2 = "Uygulamanın kullandığı dosyaların yedeği içerikte bulunmaktadır.\n"
            + "Uygulama .txt uzantılı dosyalar üzerinde işlem yapmaktadır.\n"
            + "Arayüzü tamamen manuel olarak araç kullanmadan tasarladım.\n"
            + "Projeye ait UML diyagramı proje dosyası içinde bulunmaktadır.";
    abstract void giriliyor();
}

interface KulGUI extends TemelGUI{
    void yerlestirme(int iterator);
}

interface AdmGUI extends TemelGUI{
    void yerlestirme(int iterator);
}

abstract class Adm implements AdmGUI{
    JFrame aframe = new JFrame("Nesnelerin Bankası: Yönetim Paneli: Yönetici");
    int iterate;
    JTabbedPane tp = new JTabbedPane(2);
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JPanel p5 = new JPanel();
    JPanel p6 = new JPanel();
    JPanel p7 = new JPanel();
    JPanel p8 = new JPanel();
    String ad;
    String soyad;
    String hesapid;
    String stat;
    String isimizis;
    String sira;
    
    public void actionPerformed(ActionEvent ActAdm){
        aframe.dispose();
        frame.setVisible(true);
    }
}

abstract class Perso implements AdmGUI{
    JFrame pframe = new JFrame("Nesnelerin Bankası: Yönetim Paneli: Personel");
    int iterate;
    JTabbedPane tp = new JTabbedPane(2);
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JPanel p5 = new JPanel();
    JPanel p6 = new JPanel();
    JPanel p7 = new JPanel();
    JPanel p8 = new JPanel();
    String ad;
    String soyad;
    String hesapid;
    String stat;
    String isimizis;
    String sira;
    
    public void actionPerformed(ActionEvent ActPerso){
        pframe.dispose();
        frame.setVisible(true);
    }
}

abstract class Kul implements KulGUI{
    JFrame kframe = new JFrame("Nesnelerin Bankası: Müşteri Paneli");
    JTextArea hesaptxt = new JTextArea();
    JTextArea hesaptxtp2 = new JTextArea();
    JTextArea hesaptxtp5 = new JTextArea();
    DefaultListModel hesaplistmodel = new DefaultListModel();
    JList hesaplist = new JList(hesaplistmodel);
    JList hesaplistcopy = new JList(hesaplist.getModel());
    JComboBox odemecombo = new JComboBox(new String[]{"Ödemeyi Seçiniz","Elektrik Faturası","Doğalgaz Faturası","Su Faturası","TV Faturası","Telefon Faturası","Internet Faturası","Konut Vergisi"});
    JTabbedPane tp = new JTabbedPane(2);
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JPanel p5 = new JPanel();
    JPanel p6 = new JPanel();
    JPanel p7 = new JPanel();
    JPanel p8 = new JPanel();
    String ad,soyad;
    String hesapno;
    String hesapid;
    Vector<Vector<String>> kartlar;
    Vector<Vector<String>> faturalar;
    boolean transfertype;
    
    public void actionPerformed(ActionEvent ActKul){
        kframe.dispose();
        frame.setVisible(true);
    }
}

class Admin extends Adm{
    
    int table_iterator = -1;
    
    Admin(int iterator){
        giriliyor();
        dosyaOku(iterator);
        yerlestirme(iterator);
    }
    
    private ListCellRenderer<? super String> getRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,cellHasFocus);
                listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
                return listCellRendererComponent;
            }
        };
    }
    
    public void tablefunc(DefaultTableModel tablemodel, int index){
        for(int x = tablemodel.getRowCount()-1;x>=0;x--){
            tablemodel.removeRow(x);
        }
        switch(index){
            case 2:
                try{
                    File basvuruf = new File("Approval00000.txt");
                    InputStreamReader basvurufr = new InputStreamReader(new FileInputStream(basvuruf),StandardCharsets.UTF_8);
                    BufferedReader basvurubr = new BufferedReader(basvurufr);
                    String line = new String();
                    int offset =0;
                    while((line = basvurubr.readLine())!=null){
                        line = offset + "," +basvurubr.readLine();
                        tablemodel.addRow(line.split(","));
                        offset++;
                    }
                    basvurubr.close();
                }catch(IOException e){
                    System.err.print(e.getMessage());
                }
                break;
            case 3:
                try{
                    File basvuruf = new File("SignApproval00000.txt");
                    InputStreamReader basvurufr = new InputStreamReader(new FileInputStream(basvuruf),StandardCharsets.UTF_8);
                    BufferedReader basvurubr = new BufferedReader(basvurufr);
                    String line = new String();
                    int offset = 0;
                    while((line = basvurubr.readLine())!=null){
                        if(line.split(",")[3].equals("UH")){
                            String signit[] = new String[4];
                            signit[0] = String.valueOf(offset);
                            signit[1] = line.split(",")[0];
                            signit[2] = line.split(",")[1];
                            signit[3] = "Üyelik";
                            tablemodel.addRow(signit);
                        }
                        offset++;
                    }
                    basvurubr.close();
                }catch(IOException e){
                    System.err.print(e.getMessage());
                }
                break;
            case 4:
                try{
                    File basvuruf = new File("SignApproval00000.txt");
                    InputStreamReader basvurufr = new InputStreamReader(new FileInputStream(basvuruf),StandardCharsets.UTF_8);
                    BufferedReader basvurubr = new BufferedReader(basvurufr);
                    String line = new String();
                    int offset = 0;
                    while((line = basvurubr.readLine())!=null){
                        if(line.split(",")[3].equals("H")){
                            String signit[] = new String[4];
                            signit[0] = String.valueOf(offset);
                            signit[1] = line.split(",")[0];
                            signit[2] = line.split(",")[1];
                            signit[3] = "Yeni Hesap";
                            tablemodel.addRow(signit);
                        }
                        offset++;
                    }
                    basvurubr.close();
                }catch(IOException e){
                    System.err.print(e.getMessage());
                }
                break;
            case 5:
                break;
            default:
                break;
        }
    }
    
    public void tableUpdate(boolean type,JTable table,DefaultTableModel tablemodel, int index){
        switch(index){
            case 2:
                try{
                    File basvuruf = new File("Approval00000.txt");
                    InputStreamReader basvurufr = new InputStreamReader(new FileInputStream(basvuruf),StandardCharsets.UTF_8);
                    BufferedReader basvurubr = new BufferedReader(basvurufr);
                    if(type == true){
                        int offset = Integer.parseInt(tablemodel.getValueAt(table.getSelectedRow(),0).toString());
                        System.out.print("Tamamlandı: "+ offset);

                        String line = new String();
                        String lines = new String();
                        for(int x = 0; x<offset*2;x++){
                            lines =lines + "\n"+basvurubr.readLine();
                        }
                        String kartno = basvurubr.readLine();
                        String theline = basvurubr.readLine();
                        String degisim = theline.split(",")[3];
                        String tur = theline.split(",")[1];
                        while((line = basvurubr.readLine())!=null){
                            lines = lines + "\n"+ line;
                        }
                        lines = lines.trim();
                        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        basvurubr.close();
                        
                        OutputStreamWriter basvurufw = new OutputStreamWriter(new FileOutputStream(basvuruf),StandardCharsets.UTF_8);
                        BufferedWriter basvurubw = new BufferedWriter(basvurufw);
                        basvurubw.write(lines);
                        basvurubw.close();
                        
                        if(tur.equals("Kredi")){
                            pay(kartno,"Kredi Kabul",degisim);
                        }else if(tur.equals("Kredi Kartı Limit")){
                            krediLimit(kartno,degisim);
                        }
                    }else{
                        int offset = Integer.parseInt(tablemodel.getValueAt(table.getSelectedRow(),0).toString());

                        String line = new String();
                        String lines = new String();
                        for(int x = 0; x<offset*2;x++){
                            lines =lines + "\n"+basvurubr.readLine();
                        }
                        basvurubr.readLine();
                        basvurubr.readLine();
                        while((line = basvurubr.readLine())!=null){
                            lines = lines + "\n"+ line;
                        }
                        lines = lines.trim();
                        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        basvurubr.close();
                        
                        OutputStreamWriter basvurufw = new OutputStreamWriter(new FileOutputStream(basvuruf),StandardCharsets.UTF_8);
                        BufferedWriter basvurubw = new BufferedWriter(basvurufw);
                        basvurubw.write(lines);
                        basvurubw.close();
                    }
                }catch(IOException e){
                    System.err.print(e.getMessage());
                }
                break;
            case 3:
                try{
                    File basvuruf = new File("SignApproval00000.txt");
                    InputStreamReader basvurufr = new InputStreamReader(new FileInputStream(basvuruf),StandardCharsets.UTF_8);
                    BufferedReader basvurubr = new BufferedReader(basvurufr);
                    if(type == true){
                        int offset = Integer.parseInt(tablemodel.getValueAt(table.getSelectedRow(),0).toString());
                        System.out.print("Tamamlandı: "+ offset+"\n");

                        String line = new String();
                        String lines = new String();
                        for(int x = 0; x<offset;x++){
                            lines =lines + "\n"+basvurubr.readLine();
                        }
                        String temp = basvurubr.readLine();
                        temp = temp.substring(0,temp.length()-2);
                        temp = temp + "H";
                        line = temp;
                        String kullanici[] = temp.split(",");
                        
                        File signedf = new File("Signed00000.txt");
                        InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signedf),StandardCharsets.UTF_8);
                        BufferedReader signedbr = new BufferedReader(signedfr);
                        String sline = new String();
                        String templine = new String();
                        while((templine = signedbr.readLine())!=null){
                            sline = templine;
                            signedbr.readLine();
                            signedbr.readLine();
                            signedbr.readLine();
                            signedbr.readLine();
                            signedbr.readLine();
                        }
                        sline = String.valueOf(Integer.parseInt(sline)+1);
                        int basamak = sline.length();
                        for(int x = 0;x<9-basamak;x++){
                            sline = "0"+ sline;
                        }
                        signedbr.close();
                        OutputStreamWriter signedfw = new OutputStreamWriter(new FileOutputStream(signedf,true),StandardCharsets.UTF_8);
                        BufferedWriter signedbw = new BufferedWriter(signedfw);
                        String signedstring = "\n"+sline+"\n"+";\n"+ kullanici[0]+ "\n"+kullanici[1]+"\n"+kullanici[2]+"\n________";
                        signedbw.write(signedstring);
                        signedbw.close();
                        
                        lines = lines +"\n"+line+","+sline;
                        while((line = basvurubr.readLine())!=null){
                            lines = lines + "\n"+ line;
                        }
                        lines = lines.trim();
                        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        basvurubr.close();
                        
                        OutputStreamWriter basvurufw = new OutputStreamWriter(new FileOutputStream(basvuruf),StandardCharsets.UTF_8);
                        BufferedWriter basvurubw = new BufferedWriter(basvurufw);
                        basvurubw.write(lines);
                        basvurubw.close();
                        
                        File billf = new File("Bills00000.txt");
                        OutputStreamWriter billfw = new OutputStreamWriter(new FileOutputStream(billf,true),StandardCharsets.UTF_8);
                        BufferedWriter billbw = new BufferedWriter(billfw);
                        String billstring = "\n"+sline+"\n"+";\n"+"________";
                        billbw.write(billstring);
                        billbw.close();
                        
                    }else{
                        int offset = Integer.parseInt(tablemodel.getValueAt(table.getSelectedRow(),0).toString());
                        System.out.print("Tamamlandı: "+ offset+"\n");

                        String line = new String();
                        String lines = new String();
                        for(int x = 0; x<offset;x++){
                            lines =lines + "\n"+basvurubr.readLine();
                        }
                        basvurubr.readLine();
                        while((line = basvurubr.readLine())!=null){
                            lines = lines + "\n"+ line;
                        }
                        lines = lines.trim();
                        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        basvurubr.close();
                        
                        OutputStreamWriter basvurufw = new OutputStreamWriter(new FileOutputStream(basvuruf),StandardCharsets.UTF_8);
                        BufferedWriter basvurubw = new BufferedWriter(basvurufw);
                        basvurubw.write(lines);
                        basvurubw.close();
                    }
                }catch(IOException e){
                    System.err.print(e.getMessage());
                }
                break;
            case 4:
                try{
                    File basvuruf = new File("SignApproval00000.txt");
                    InputStreamReader basvurufr = new InputStreamReader(new FileInputStream(basvuruf),StandardCharsets.UTF_8);
                    BufferedReader basvurubr = new BufferedReader(basvurufr);
                    if(type == true){
                        int offset = Integer.parseInt(tablemodel.getValueAt(table.getSelectedRow(),0).toString());
                        System.out.print("Tamamlandı: "+ offset+"\n");

                        String line = new String();
                        String lines = new String();
                        for(int x = 0; x<offset;x++){
                            lines =lines + "\n"+basvurubr.readLine();
                        }
                        String temp = basvurubr.readLine();
                        String kullanici[] = temp.split(",");
                        while((line = basvurubr.readLine())!=null){
                            lines = lines + "\n"+ line;
                        }
                        lines = lines.trim();
                        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        basvurubr.close();
                        
                        OutputStreamWriter basvurufw = new OutputStreamWriter(new FileOutputStream(basvuruf),StandardCharsets.UTF_8);
                        BufferedWriter basvurubw = new BufferedWriter(basvurufw);
                        basvurubw.write(lines);
                        basvurubw.close();
                        
                        File seizedf = new File("Seized510001.txt");
                        InputStreamReader seizedfr = new InputStreamReader(new FileInputStream(seizedf),StandardCharsets.UTF_8);
                        BufferedReader seizedbr = new BufferedReader(seizedfr);
                        String seizedstring = new String();
                        String tempstring = new String();
                        while((tempstring = seizedbr.readLine())!=null){
                            seizedstring = tempstring;
                            seizedbr.readLine();
                        }
                        String seizedstring2 = seizedstring.replace(" ","").substring(6, 15);
                        seizedstring2 = String.valueOf(Integer.parseInt(seizedstring2)+1);
                        
                        int basamak = seizedstring2.length();
                        for(int x =0;x<9-basamak;x++){
                            seizedstring2 = "0"+seizedstring2;
                        }
                        seizedstring = seizedstring.replace(" ","").replace(seizedstring.replace(" ","").substring(6, 15),seizedstring2);
                        
                        int checkdigit = 0;
                        for(int x =seizedstring.length()-3;x>0;x=x-2){
                            checkdigit = checkdigit + Integer.parseInt(seizedstring.substring(x, x+1));
                        }
                        for(int x =seizedstring.length()-2;x>0;x=x-2){
                            if((Integer.parseInt(seizedstring.substring(x, x+1))*2)>9){
                                checkdigit = checkdigit + ((Integer.parseInt(seizedstring.substring(x, x+1))*2)-9);
                            }else{
                                checkdigit = checkdigit + (Integer.parseInt(seizedstring.substring(x, x+1))*2);
                            }
                        }
                        checkdigit = 10-(checkdigit%10);
                        seizedstring = seizedstring.substring(0, 15)+String.valueOf(checkdigit);
                        seizedstring = seizedstring.substring(0, 4)+" "+seizedstring.substring(4, 8)+" "+seizedstring.substring(8, 12)+" "+seizedstring.substring(12, 16);
                        seizedbr.close();
                        
                        OutputStreamWriter seizedfw = new OutputStreamWriter(new FileOutputStream(seizedf,true),StandardCharsets.UTF_8);
                        BufferedWriter seizedbw = new BufferedWriter(seizedfw);
                        seizedbw.write("\n"+seizedstring+"\n"+";");
                        seizedbw.close();
                        
                        File signedf = new File("Signed00000.txt");
                        InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signedf),StandardCharsets.UTF_8);
                        BufferedReader signedbr = new BufferedReader(signedfr);
                        int where = Integer.parseInt(kullanici[4])-1;
                        String signedlines = new String();
                        for(int x =0;x<where*6;x++){
                            signedlines = signedlines + "\n" + signedbr.readLine();
                        }
                        signedlines = signedlines + "\n" + signedbr.readLine();
                        String signedtemp=new String();
                        if((signedtemp=signedbr.readLine()).equals(";")){
                            signedlines = signedlines + "\nB,"+seizedstring+",2000.00,null,0;";
                        }else{
                            signedlines = signedlines + "\nB,"+seizedstring+",2000.00,null,0;"+signedbr.readLine();
                        }
                        
                        while((signedtemp = signedbr.readLine())!=null){
                            signedlines = signedlines + "\n"+signedtemp;
                        }
                        signedbr.close();
                        
                        OutputStreamWriter signedfw = new OutputStreamWriter(new FileOutputStream(signedf),StandardCharsets.UTF_8);
                        BufferedWriter signedbw = new BufferedWriter(signedfw);
                        signedlines = signedlines.trim();
                        signedlines = signedlines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        signedbw.write(signedlines);
                        signedbw.close();
                        
                    }else{
                        int offset = Integer.parseInt(tablemodel.getValueAt(table.getSelectedRow(),0).toString());
                        System.out.print("Tamamlandı: "+ offset+"\n");

                        String line = new String();
                        String lines = new String();
                        for(int x = 0; x<offset;x++){
                            lines =lines + "\n"+basvurubr.readLine();
                        }
                        basvurubr.readLine();
                        while((line = basvurubr.readLine())!=null){
                            lines = lines + "\n"+ line;
                        }
                        lines = lines.trim();
                        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        basvurubr.close();
                        
                        OutputStreamWriter basvurufw = new OutputStreamWriter(new FileOutputStream(basvuruf),StandardCharsets.UTF_8);
                        BufferedWriter basvurubw = new BufferedWriter(basvurufw);
                        basvurubw.write(lines);
                        basvurubw.close();
                    }
                }catch(IOException e){
                    System.err.print(e.getMessage());
                }
                break;
            default:
                break;
        }
    }
    
    public void krediLimit(String kartno,String limit){
        try{
        File signed = new File("Signed00000.txt");
        InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signed),StandardCharsets.UTF_8);
        BufferedReader signedbr = new BufferedReader(signedfr);
        String lines = new String();
        String line = new String();
        while((line = signedbr.readLine())!=null){
            if(line.contains(kartno)){
                break;
            }else{
               lines = lines +"\n"+ line;
            }
        }
        String temp[] = line.split(";");
        String temptemp[] = null;
        int tempit = 0;
        for(;tempit <temp.length;tempit++){
            if(temp[tempit].contains(kartno)){
                temptemp = temp[tempit].split(",");
                break;
            }
        }
        DecimalFormat dec = new DecimalFormat("#0.00");
        line = line.replace(kartno+","+temptemp[2]+","+temptemp[3],
        kartno+","+dec.format(Double.parseDouble(temptemp[2])).replace(",", ".")+","+dec.format(Double.parseDouble(limit)).replace(",", "."));
        lines = lines+"\n" + line;
        while((line = signedbr.readLine())!=null){
            lines = lines+"\n" + line;
        }
        lines = lines.trim();
        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
        signedbr.close();
        OutputStreamWriter signedfw = new OutputStreamWriter(new FileOutputStream(signed,false),StandardCharsets.UTF_8);
        BufferedWriter signedbw = new BufferedWriter(signedfw);
        signedbw.write(lines);
        signedbw.close();
        
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void pay(String kartno,String aciklama,String degisim){
        hareketekle(kartno,aciklama,"+"+degisim);
        try{
        File signed = new File("Signed00000.txt");
        InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signed),StandardCharsets.UTF_8);
        BufferedReader signedbr = new BufferedReader(signedfr);
        String lines = new String();
        String line = new String();
        while((line = signedbr.readLine())!=null){
            if(line.contains(kartno)){
                break;
            }else{
               lines = lines +"\n"+ line;
            }
        }
        String temp[] = line.split(";");
        String temptemp[] = null;
        int tempit = 0;
        for(;tempit <temp.length;tempit++){
            if(temp[tempit].contains(kartno)){
                temptemp = temp[tempit].split(",");
                break;
            }
        }
        DecimalFormat dec = new DecimalFormat("#0.00");
        line = line.replace(kartno+","+temptemp[2],
        kartno+","+dec.format(Double.parseDouble(temptemp[2])+Double.parseDouble(degisim)).replace(',', '.'));
        lines = lines+"\n" + line;
        while((line = signedbr.readLine())!=null){
            lines = lines+"\n" + line;
        }
        lines = lines.trim();
        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
        signedbr.close();
        OutputStreamWriter signedfw = new OutputStreamWriter(new FileOutputStream(signed,false),StandardCharsets.UTF_8);
        BufferedWriter signedbw = new BufferedWriter(signedfw);
        signedbw.write(lines);
        signedbw.close();
        
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void hareketekle(String kartno,String aciklama, String degisim){
        String filename = null;
        try{
            filename = "Seized"+kartno.substring(0,4)
                            + kartno.substring(5, 7)
                            +".txt";
            File hareket = new File(filename);
            InputStreamReader hareketfr = new InputStreamReader(new FileInputStream(hareket),StandardCharsets.UTF_8);
            BufferedReader hareketbr = new BufferedReader(hareketfr);
            String lines = new String();
            String line = new String();
            while((line=hareketbr.readLine()) !=null){
                lines = lines +"\n"+line;
                if(line.equals(kartno)){break;}
            }
            String theline = hareketbr.readLine();
            if(!theline.equals(";")){
                theline = aciklama+","+degisim+";"+theline;
                lines = lines+"\n"+theline;
            }else{
                theline = aciklama+","+degisim+";";
                lines = lines+"\n"+theline;
            }
            while((line=hareketbr.readLine())!=null){
                lines = lines + "\n"+line;
            }
            lines = lines.trim();
            lines = lines.replaceAll("(?m)^[ \t]*\r?\n", "");
            hareketbr.close();
            OutputStreamWriter hareketfw = new OutputStreamWriter(new FileOutputStream(hareket),StandardCharsets.UTF_8);
            BufferedWriter hareketbw = new BufferedWriter(hareketfw);
            hareketbw.write(lines);
            hareketbw.close();
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void dosyaOku(int iterator){
        try{
            File adminf = new File("Admin000.txt");
            InputStreamReader adminfr = new InputStreamReader(new FileInputStream(adminf),StandardCharsets.UTF_8);
            BufferedReader adminbr = new BufferedReader(adminfr);
            for(int x =0;x<(iterator*6);x++){
                adminbr.readLine();
            }
            hesapid = new String(adminbr.readLine());
            ad = new String(adminbr.readLine());
            soyad = new String(adminbr.readLine());
            stat = new String(adminbr.readLine().split(",")[1]);
            adminbr.close();
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void yerlestirme(int iterator){
        
        int kx,ky;
        kx = p1.getSize().width;
        ky = p1.getSize().height;
        
        //panel 1
            
            JLabel girislabel = new JLabel("Yönetici olarak giriş yaptınız.");
            girislabel.setBounds(50,60,200,30);
            p1.add(girislabel);
            JLabel admin = new JLabel("Yönetici: " + ad + " " + soyad);
            admin.setBounds(50,110,200,30);
            p1.add(admin);
            JLabel adminid = new JLabel("Personel ID: " + hesapid);
            adminid.setBounds(50,160,200,30);
            p1.add(adminid);
        
        //panel 2
            
            JLabel basvurulabel = new JLabel("Başvuru Listesi");
            basvurulabel.setFont(basvurulabel.getFont().deriveFont(15f));
            Arayuz.mybounds(basvurulabel, 150, 30, kx, 40);
            p2.add(basvurulabel);
            JTable basvurutable = new JTable();
            DefaultTableModel dtmbasvuru = new DefaultTableModel();
            String dtmbasvuruheader[] = new String[]{"ID","Ad-Soyad","Talep","Neden","Miktar"};
            dtmbasvuru.setColumnIdentifiers(dtmbasvuruheader);
            basvurutable.setModel(dtmbasvuru);
            basvurutable.setRowSelectionAllowed(true);
            basvurutable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            basvurutable.setDefaultEditor(Object.class,null);
            JScrollPane basvurusp = new JScrollPane(basvurutable);
            Arayuz.mybounds(basvurusp, kx-300, ky-150, kx-200, 100);
            p2.add(basvurusp);
            JButton basvurukabul = new JButton("Kabul Et");
            basvurukabul.setSize(100,16);
            basvurukabul.setVisible(false);
            p2.add(basvurukabul);
            JButton basvurured = new JButton("Reddet");
            basvurured.setSize(100,16);
            basvurured.setVisible(false);
            p2.add(basvurured);
            
        //panel 3
            
            JLabel uyelabel = new JLabel("Üye Başvuru Listesi");
            uyelabel.setFont(uyelabel.getFont().deriveFont(15f));
            Arayuz.mybounds(uyelabel, 150, 30, kx, 40);
            p3.add(uyelabel);
            JTable uyetable = new JTable();
            DefaultTableModel dtmuye = new DefaultTableModel();
            String dtmuyeheader[] = new String[]{"ID","Ad","Soyad","Talep"};
            dtmuye.setColumnIdentifiers(dtmuyeheader);
            uyetable.setModel(dtmuye);
            uyetable.setRowSelectionAllowed(true);
            uyetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            uyetable.setDefaultEditor(Object.class,null);
            JScrollPane uyesp = new JScrollPane(uyetable);
            Arayuz.mybounds(uyesp, kx-300, ky-150, kx-200, 100);
            p3.add(uyesp);
            JButton uyekabul = new JButton("Kabul Et");
            uyekabul.setSize(100,16);
            uyekabul.setVisible(false);
            p3.add(uyekabul);
            JButton uyered = new JButton("Reddet");
            uyered.setSize(100,16);
            uyered.setVisible(false);
            p3.add(uyered);
            
        //panel 4
            
            JLabel hesaplabel = new JLabel("Hesap Başvuru Listesi");
            hesaplabel.setFont(hesaplabel.getFont().deriveFont(15f));
            Arayuz.mybounds(hesaplabel, 200, 30, kx, 40);
            p4.add(hesaplabel);
            JTable hesaptable = new JTable();
            DefaultTableModel dtmhesap = new DefaultTableModel();
            String dtmhesapheader[] = new String[]{"ID","Ad","Soyad","Talep"};
            dtmhesap.setColumnIdentifiers(dtmhesapheader);
            hesaptable.setModel(dtmhesap);
            hesaptable.setRowSelectionAllowed(true);
            hesaptable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            hesaptable.setDefaultEditor(Object.class,null);
            JScrollPane hesapsp = new JScrollPane(hesaptable);
            Arayuz.mybounds(hesapsp, kx-300, ky-150, kx-200, 100);
            p4.add(hesapsp);
            JButton hesapkabul = new JButton("Kabul Et");
            hesapkabul.setSize(100,16);
            hesapkabul.setVisible(false);
            p4.add(hesapkabul);
            JButton hesapred = new JButton("Reddet");
            hesapred.setSize(100,16);
            hesapred.setVisible(false);
            p4.add(hesapred);
            
        //panel 5
            
            JLabel musterilabel = new JLabel("Müşteri Ara");
            musterilabel.setFont(musterilabel.getFont().deriveFont(15f));
            Arayuz.mybounds(musterilabel, 150, 30, kx, 40);
            p5.add(musterilabel);
            JLabel musterihesaplabel = new JLabel("Hesap NO:");
            musterihesaplabel.setBounds(50,160,100,30);
            p5.add(musterihesaplabel);
            JTextField musterihesap = new JTextField();
            musterihesap.setBounds(150,160,150,30);
            p5.add(musterihesap);
            JLabel musteriIDlabel = new JLabel("ID:");
            musteriIDlabel.setBounds(50,210,100,30);
            p5.add(musteriIDlabel);
            JTextField musteriID = new JTextField();
            musteriID.setBounds(150,210,150,30);
            p5.add(musteriID);
            JLabel musteriadlabel = new JLabel("Ad:");
            musteriadlabel.setBounds(50,260,100,30);
            p5.add(musteriadlabel);
            JTextField musteriad = new JTextField();
            musteriad.setBounds(150,260,150,30);
            p5.add(musteriad);
            JLabel musterisoyadlabel = new JLabel("Soyad:");
            musterisoyadlabel.setBounds(50,310,100,30);
            p5.add(musterisoyadlabel);
            JTextField musterisoyad = new JTextField();
            musterisoyad.setBounds(150,310,150,30);
            p5.add(musterisoyad);
            JButton musteriara = new JButton("ARA");
            musteriara.setFont(musteriara.getFont().deriveFont(15f));
            musteriara.setBounds(150,360,150,50);
            p5.add(musteriara);
            JTable musteritable = new JTable();
            DefaultTableModel dtmmusteri = new DefaultTableModel();
            String dtmmusteriheader[] = new String[]{"ID","Ad","Soyad","Hesap"};
            dtmmusteri.setColumnIdentifiers(dtmmusteriheader);
            musteritable.setModel(dtmmusteri);
            musteritable.setRowSelectionAllowed(true);
            musteritable.setDefaultEditor(Object.class,null);
            JScrollPane musterisp = new JScrollPane(musteritable);
            Arayuz.mybounds(musterisp, kx/2+50, ky-150, kx+350, 100);
            p5.add(musterisp);
            
        //panel 6
        
            JButton hesapbuton = new JButton("Parola Değiştir");
            Arayuz.mybounds(hesapbuton,150,30,kx,300);
            p6.add(hesapbuton);
            JPasswordField hesapyenisifre = new JPasswordField();
            Arayuz.mybounds(hesapyenisifre,150,30,kx,150);
            p6.add(hesapyenisifre);
            JPasswordField hesaponaysifre = new JPasswordField();
            Arayuz.mybounds(hesaponaysifre,150,30,kx,200);
            p6.add(hesaponaysifre);
            JPasswordField hesapeskisifre = new JPasswordField();
            Arayuz.mybounds(hesapeskisifre,150,30,kx,250);
            p6.add(hesapeskisifre);
            JLabel hesaplabel1 = new JLabel("Yeni Parola: ");
            Arayuz.mybounds(hesaplabel1,150 , 30,kx-200, 150);
            p6.add(hesaplabel1);
            JLabel hesaplabel2 = new JLabel("Yeni Parola Onay: ");
            Arayuz.mybounds(hesaplabel2,150,30 ,kx-200, 200);
            p6.add(hesaplabel2);
            JLabel hesaplabel3 = new JLabel("Eski Parola");
            Arayuz.mybounds(hesaplabel3,150,30, kx-200, 250);
            p6.add(hesaplabel3);
            
        //exit
            
            oturumkapat.setFont(oturumkapat.getFont().deriveFont(15f));
            oturumkapat.setBackground(Color.red);
            oturumkapat.setForeground(Color.white);
            Arayuz.mybounds(oturumkapat,150,40,kx,ky/2-50);
            exit.add(oturumkapat);
            JLabel izin = new JLabel("Oturumu kapatmak istediğinizden emin misiniz?");
            izin.setFont(izin.getFont().deriveFont(15f));
            Arayuz.mybounds(izin,350,30,kx,ky/2-100);
            exit.add(izin);
            
        //listener field
        tp.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent gecis){
                if(tp.getSelectedIndex()==0){
                    
                }
                if(tp.getSelectedIndex()==1){
                    tablefunc(dtmbasvuru,2);
                    hesapkabul.setVisible(false);
                    hesapred.setVisible(false);
                    uyekabul.setVisible(false);
                    uyered.setVisible(false);
                }
                if(tp.getSelectedIndex()==2){
                    tablefunc(dtmuye,3);
                    hesapkabul.setVisible(false);
                    hesapred.setVisible(false);
                    basvurukabul.setVisible(false);
                    basvurured.setVisible(false);
                }
                if(tp.getSelectedIndex()==3){
                    tablefunc(dtmhesap,4);
                    uyekabul.setVisible(false);
                    uyered.setVisible(false);
                    basvurukabul.setVisible(false);
                    basvurured.setVisible(false);
                }
                if(tp.getSelectedIndex()==4){
                    
                }
                if(tp.getSelectedIndex()==5){
                   
                }
                if(tp.getSelectedIndex()==6){
                    
                }
            }
        });
        
        musteriara.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String kullanici_ad = musteriad.getText();
                if(kullanici_ad.contains(" ")&&kullanici_ad.length()>2){
                    kullanici_ad = kullanici_ad.split(" ")[0].substring(0,1).toUpperCase()+kullanici_ad.split(" ")[0].substring(1).toLowerCase()+" "+kullanici_ad.split(" ")[1].substring(0, 1).toUpperCase()+kullanici_ad.split(" ")[1].substring(1).toLowerCase();
                }else if(kullanici_ad.length()>0){
                    kullanici_ad = kullanici_ad.substring(0, 1).toUpperCase()+kullanici_ad.substring(1).toLowerCase();
                }
                String kullanici_soyad = musterisoyad.getText();
                kullanici_soyad = kullanici_soyad.toUpperCase();
                String kullanici_id = musteriID.getText();
                String kullanici_kart = musterihesap.getText();
                
                String tempad = new String();
                String tempsoyad = new String();
                String tempid = new String();
                String tempkart[];
                String temp = new String();
                
                for(int x = dtmmusteri.getRowCount()-1;x>=0;x--){
                    dtmmusteri.removeRow(x);
                }
                try{
                    File signedf = new File("Signed00000.txt");
                    InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signedf),StandardCharsets.UTF_8);
                    BufferedReader signedbr = new BufferedReader(signedfr);
                    
                    while((tempid = signedbr.readLine())!=null){
                        temp = signedbr.readLine();
                        tempkart = temp.split(";");
                        for(int x = 0;x<tempkart.length;x++){
                            tempkart[x] = tempkart[x].split(",")[1];
                        }
                        tempad = signedbr.readLine();
                        tempsoyad = signedbr.readLine();
                        signedbr.readLine();
                        signedbr.readLine();
                        System.out.print(tempid+"\n"+tempad+"\n"+tempsoyad+"\n"+tempkart.toString());
                        
                        if((!tempid.equals("")&&tempid.contains(kullanici_id))||((tempid.equals(""))&&!tempid.contains(kullanici_id))){
                            if((!tempad.equals("")&&tempad.contains(kullanici_ad))||((tempad.equals(""))&&!tempad.contains(kullanici_ad))){
                                if((!tempsoyad.equals("")&&tempsoyad.contains(kullanici_soyad))||((tempsoyad.equals(""))&&!tempsoyad.contains(kullanici_soyad))){
                                    if(tempkart.length>0){
                                        for(String x : tempkart){
                                            if((!tempkart.equals("")&&x.contains(kullanici_kart))||((tempkart.equals(""))&&!x.contains(kullanici_kart))){
                                                dtmmusteri.addRow(new String[]{tempid,tempad,tempsoyad,x});
                                            }
                                        }
                                    }else{
                                        dtmmusteri.addRow(new String[]{tempid,tempad,tempsoyad,"-"});
                                    }
                                }
                            }
                        }
                        
                    }
                    signedbr.close();
                }catch(IOException ex){
                    System.err.print(ex.getMessage());
                }
            }
        });
        
        basvurutable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                if(basvurutable.getSelectedRow()!=-1){
                    iterate=basvurutable.getSelectedRow();
                    System.out.print("Seçilen satır: "+iterate+ "\n");
                    int y = 100+((basvurutable.getSelectedRow()+1)*basvurutable.getRowHeight()),x=basvurusp.getLocation().x+basvurusp.getSize().width;
                    basvurukabul.setLocation(x+10,y);
                    basvurured.setLocation(x+120, y);
                    basvurukabul.setVisible(true);
                    basvurured.setVisible(true);
                }
        }});
            
        basvurukabul.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tableUpdate(true,basvurutable,dtmbasvuru,2);
                tablefunc(dtmbasvuru,2);
                basvurured.setVisible(false);
                basvurukabul.setVisible(false);
                
        }});
            
        basvurured.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tableUpdate(false,basvurutable,dtmbasvuru,2);
                tablefunc(dtmbasvuru,2);
                basvurured.setVisible(false);
                basvurukabul.setVisible(false);
        }});
        
        hesaptable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                if(hesaptable.getSelectedRow()!=-1){
                    iterate=hesaptable.getSelectedRow();
                    System.out.print("Seçilen satır: "+iterate+ "\n");
                    int y = 100+((hesaptable.getSelectedRow()+1)*hesaptable.getRowHeight()),x=hesapsp.getLocation().x+hesapsp.getSize().width;
                    hesapkabul.setLocation(x+10,y);
                    hesapred.setLocation(x+120, y);
                    hesapkabul.setVisible(true);
                    hesapred.setVisible(true);
                }
        }});
            
        hesapkabul.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tableUpdate(true,hesaptable,dtmhesap,4);
                tablefunc(dtmhesap,4);
                hesapred.setVisible(false);
                hesapkabul.setVisible(false);
        }});
            
        hesapred.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tableUpdate(false,hesaptable,dtmhesap,4);
                tablefunc(dtmhesap,4);
                hesapred.setVisible(false);
                hesapkabul.setVisible(false);
        }});
        
        uyetable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                if(uyetable.getSelectedRow()!=-1){
                    iterate=uyetable.getSelectedRow();
                    System.out.print("Seçilen satır: "+iterate+ "\n");
                    int y = 100+((uyetable.getSelectedRow()+1)*uyetable.getRowHeight()),x=uyesp.getLocation().x+uyesp.getSize().width;
                    uyekabul.setLocation(x+10,y);
                    uyered.setLocation(x+120, y);
                    uyekabul.setVisible(true);
                    uyered.setVisible(true);
                }
        }});
            
        uyekabul.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tableUpdate(true,uyetable,dtmuye,3);
                tablefunc(dtmuye,3);
                uyered.setVisible(false);
                uyekabul.setVisible(false);
        }});
            
        uyered.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tableUpdate(false,uyetable,dtmuye,3);
                tablefunc(dtmuye,3);
                uyered.setVisible(false);
                uyekabul.setVisible(false);
        }});
        
        hesapbuton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String yeni = new String(hesapyenisifre.getPassword());
                String onay = new String(hesaponaysifre.getPassword());
                String eski = new String(hesapeskisifre.getPassword());
                if(yeni.equals(onay)&& !yeni.isEmpty() && !eski.isEmpty()){
                    String eskisifre = null;
                    try{
                        File adminf = new File("Admin000.txt");
                        InputStreamReader adminfr = new InputStreamReader(new FileInputStream(adminf),StandardCharsets.UTF_8);
                        BufferedReader adminbr = new BufferedReader(adminfr);
                        for(int x = 0;x<(iterator*6);x++){
                            adminbr.readLine();
                        }
                        adminbr.readLine();
                        adminbr.readLine();
                        adminbr.readLine();
                        adminbr.readLine();
                        eskisifre = adminbr.readLine();
                        adminbr.close();
                        }catch(IOException ex){
                            System.out.print(ex.getMessage());
                    }
                    if(eski.equals(eskisifre)){
                        try{
                        File adminf = new File("Admin000.txt");
                        InputStreamReader adminfr = new InputStreamReader(new FileInputStream(adminf),StandardCharsets.UTF_8);
                        BufferedReader adminbr = new BufferedReader(adminfr);
                        String line = new String();
                        String lines = new String();
                        for(int x = 0;x<(iterator*6);x++){
                            lines = lines + "\n"+adminbr.readLine();
                        }
                        lines = lines + "\n" +adminbr.readLine();
                        lines = lines + "\n" +adminbr.readLine();
                        lines = lines + "\n" +adminbr.readLine();
                        lines = lines + "\n" +adminbr.readLine();
                        adminbr.readLine();
                        lines = lines + "\n"+yeni;
                        while((line = adminbr.readLine())!=null){
                            lines = lines + "\n"+line;
                        }
                        lines = lines.trim();
                        lines = lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        adminbr.close();
                        OutputStreamWriter adminfw = new OutputStreamWriter(new FileOutputStream(adminf),StandardCharsets.UTF_8);
                        BufferedWriter adminbw = new BufferedWriter(adminfw);
                        adminbw.write(lines);
                        adminbw.close();
                        }catch(IOException ex){
                            System.out.print(ex.getMessage());
                    }
                        JOptionPane.showMessageDialog(new JFrame(),"İşlem Başarılı","BAŞARILI",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(new JFrame(),"Bilgileri Eksik Veya Yanlış Girdiniz","UYARI",JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"Bilgileri Eksik Veya Yanlış Girdiniz","UYARI",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        oturumkapat.addActionListener(this);
    }
    
    public void giriliyor(){
        
        aframe.add(tp);
        aframe.setResizable(false);
        aframe.setSize(1000,700);
        frame.setVisible(false);
        aframe.setLocation(200,200);
        aframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        tp.addTab("Personel",null,p1,"Hesap bilgileri");
        tp.addTab("Başvuru Kabul",null,p2,"Başvuru onay işlemleri");
        tp.addTab("Üye Kabul",null,p3,"Üye işlemlerini onayla");
        tp.addTab("Hesap Kabul",null,p4,"Hesap açma işlemlerini onayla");
        tp.addTab("Müşteri Ara",null,p5,"Müşteri arama");
        tp.addTab("Hesap Ayarları",null,p6,"Hesap bilgilerinizi değiştirin");
        tp.addTab("Oturumu Kapat",null,exit,null);
        
        tp.setBounds(0,0,1000,700);
        tp.setBackground(Color.lightGray);
        tp.setForeground(Color.green);
        tp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tp.setOpaque(true);
        
        aframe.setVisible(true);
        aframe.setLayout(null);
        
        {
            p1.setBorder(BorderFactory.createLineBorder(Color.lightGray, 10));
            p1.setBackground(Color.WHITE);
            p1.setLayout(null);
            p2.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p2.setBackground(Color.WHITE);
            p2.setLayout(null);
            p3.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p3.setBackground(Color.WHITE);
            p3.setLayout(null);
            p4.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p4.setBackground(Color.WHITE);
            p4.setLayout(null);
            p5.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p5.setBackground(Color.WHITE);
            p5.setLayout(null);
            p6.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p6.setBackground(Color.WHITE);
            p6.setLayout(null);
            exit.setBackground(Color.WHITE);
            exit.setLayout(null);
            for(int x=0;x<6;x++){
                tp.setBackgroundAt(x, new Color(128,128,128));
                tp.setMnemonicAt(x, KeyEvent.VK_0);
            }
            tp.setBackgroundAt(6, Color.red);
            tp.setForegroundAt(6, Color.white);
        }
    }
    
}

class Personel extends Perso{
    
    Personel(int iterator){
        giriliyor();
        dosyaOku(iterator);
        yerlestirme(iterator);
    }
    
    int table_iterator = -1;
    
    private ListCellRenderer<? super String> getRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,cellHasFocus);
                listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
                return listCellRendererComponent;
            }
        };
    }
    
    public void tablefunc(DefaultTableModel tablemodel, int index){
        for(int x = tablemodel.getRowCount()-1;x>=0;x--){
            tablemodel.removeRow(x);
        }
        switch(index){
            case 2:
                try{
                    File basvuruf = new File("Approval00000.txt");
                    InputStreamReader basvurufr = new InputStreamReader(new FileInputStream(basvuruf),StandardCharsets.UTF_8);
                    BufferedReader basvurubr = new BufferedReader(basvurufr);
                    String line = new String();
                    int offset =0;
                    while((line = basvurubr.readLine())!=null){
                        line = offset + "," +basvurubr.readLine();
                        tablemodel.addRow(line.split(","));
                        offset++;
                    }
                    basvurubr.close();
                }catch(IOException e){
                    System.err.print(e.getMessage());
                }
                break;
            case 3:
                try{
                    File basvuruf = new File("SignApproval00000.txt");
                    InputStreamReader basvurufr = new InputStreamReader(new FileInputStream(basvuruf),StandardCharsets.UTF_8);
                    BufferedReader basvurubr = new BufferedReader(basvurufr);
                    String line = new String();
                    int offset = 0;
                    while((line = basvurubr.readLine())!=null){
                        if(line.split(",")[3].equals("UH")){
                            String signit[] = new String[4];
                            signit[0] = String.valueOf(offset);
                            signit[1] = line.split(",")[0];
                            signit[2] = line.split(",")[1];
                            signit[3] = "Üyelik";
                            tablemodel.addRow(signit);
                        }
                        offset++;
                    }
                    basvurubr.close();
                }catch(IOException e){
                    System.err.print(e.getMessage());
                }
                break;
            case 4:
                try{
                    File basvuruf = new File("SignApproval00000.txt");
                    InputStreamReader basvurufr = new InputStreamReader(new FileInputStream(basvuruf),StandardCharsets.UTF_8);
                    BufferedReader basvurubr = new BufferedReader(basvurufr);
                    String line = new String();
                    int offset = 0;
                    while((line = basvurubr.readLine())!=null){
                        if(line.split(",")[3].equals("H")){
                            String signit[] = new String[4];
                            signit[0] = String.valueOf(offset);
                            signit[1] = line.split(",")[0];
                            signit[2] = line.split(",")[1];
                            signit[3] = "Yeni Hesap";
                            tablemodel.addRow(signit);
                        }
                        offset++;
                    }
                    basvurubr.close();
                }catch(IOException e){
                    System.err.print(e.getMessage());
                }
                break;
            case 5:
                break;
            default:
                break;
        }
    }
    
    public void tableUpdate(boolean type,JTable table,DefaultTableModel tablemodel, int index){
        switch(index){
            case 2:
                try{
                    File basvuruf = new File("Approval00000.txt");
                    InputStreamReader basvurufr = new InputStreamReader(new FileInputStream(basvuruf),StandardCharsets.UTF_8);
                    BufferedReader basvurubr = new BufferedReader(basvurufr);
                    if(type == true){
                        int offset = Integer.parseInt(tablemodel.getValueAt(table.getSelectedRow(),0).toString());
                        System.out.print("Tamamlandı: "+ offset);

                        String line = new String();
                        String lines = new String();
                        for(int x = 0; x<offset*2;x++){
                            lines =lines + "\n"+basvurubr.readLine();
                        }
                        String kartno = basvurubr.readLine();
                        String theline = basvurubr.readLine();
                        String degisim = theline.split(",")[3];
                        String tur = theline.split(",")[1];
                        while((line = basvurubr.readLine())!=null){
                            lines = lines + "\n"+ line;
                        }
                        lines = lines.trim();
                        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        basvurubr.close();
                        
                        OutputStreamWriter basvurufw = new OutputStreamWriter(new FileOutputStream(basvuruf),StandardCharsets.UTF_8);
                        BufferedWriter basvurubw = new BufferedWriter(basvurufw);
                        basvurubw.write(lines);
                        basvurubw.close();
                        
                        if(tur.equals("Kredi")){
                            pay(kartno,"Kredi Kabul",degisim);
                        }else if(tur.equals("Kredi Kartı Limit")){
                            krediLimit(kartno,degisim);
                        }
                    }else{
                        int offset = Integer.parseInt(tablemodel.getValueAt(table.getSelectedRow(),0).toString());

                        String line = new String();
                        String lines = new String();
                        for(int x = 0; x<offset*2;x++){
                            lines =lines + "\n"+basvurubr.readLine();
                        }
                        basvurubr.readLine();
                        basvurubr.readLine();
                        while((line = basvurubr.readLine())!=null){
                            lines = lines + "\n"+ line;
                        }
                        lines = lines.trim();
                        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        basvurubr.close();
                        
                        OutputStreamWriter basvurufw = new OutputStreamWriter(new FileOutputStream(basvuruf),StandardCharsets.UTF_8);
                        BufferedWriter basvurubw = new BufferedWriter(basvurufw);
                        basvurubw.write(lines);
                        basvurubw.close();
                    }
                }catch(IOException e){
                    System.err.print(e.getMessage());
                }
                break;
            case 3:
                try{
                    File basvuruf = new File("SignApproval00000.txt");
                    InputStreamReader basvurufr = new InputStreamReader(new FileInputStream(basvuruf),StandardCharsets.UTF_8);
                    BufferedReader basvurubr = new BufferedReader(basvurufr);
                    if(type == true){
                        int offset = Integer.parseInt(tablemodel.getValueAt(table.getSelectedRow(),0).toString());
                        System.out.print("Tamamlandı: "+ offset+"\n");

                        String line = new String();
                        String lines = new String();
                        for(int x = 0; x<offset;x++){
                            lines =lines + "\n"+basvurubr.readLine();
                        }
                        String temp = basvurubr.readLine();
                        temp = temp.substring(0,temp.length()-2);
                        temp = temp + "H";
                        line = temp;
                        String kullanici[] = temp.split(",");
                        
                        File signedf = new File("Signed00000.txt");
                        InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signedf),StandardCharsets.UTF_8);
                        BufferedReader signedbr = new BufferedReader(signedfr);
                        String sline = new String();
                        String templine = new String();
                        while((templine = signedbr.readLine())!=null){
                            sline = templine;
                            signedbr.readLine();
                            signedbr.readLine();
                            signedbr.readLine();
                            signedbr.readLine();
                            signedbr.readLine();
                        }
                        sline = String.valueOf(Integer.parseInt(sline)+1);
                        int basamak = sline.length();
                        for(int x = 0;x<9-basamak;x++){
                            sline = "0"+ sline;
                        }
                        signedbr.close();
                        OutputStreamWriter signedfw = new OutputStreamWriter(new FileOutputStream(signedf,true),StandardCharsets.UTF_8);
                        BufferedWriter signedbw = new BufferedWriter(signedfw);
                        String signedstring = "\n"+sline+"\n"+";\n"+ kullanici[0]+ "\n"+kullanici[1]+"\n"+kullanici[2]+"\n________";
                        signedbw.write(signedstring);
                        signedbw.close();
                        
                        lines = lines +"\n"+line+","+sline;
                        while((line = basvurubr.readLine())!=null){
                            lines = lines + "\n"+ line;
                        }
                        lines = lines.trim();
                        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        basvurubr.close();
                        
                        OutputStreamWriter basvurufw = new OutputStreamWriter(new FileOutputStream(basvuruf),StandardCharsets.UTF_8);
                        BufferedWriter basvurubw = new BufferedWriter(basvurufw);
                        basvurubw.write(lines);
                        basvurubw.close();
                        
                        File billf = new File("Bills00000.txt");
                        OutputStreamWriter billfw = new OutputStreamWriter(new FileOutputStream(billf,true),StandardCharsets.UTF_8);
                        BufferedWriter billbw = new BufferedWriter(billfw);
                        String billstring = "\n"+sline+"\n"+";\n"+"________";
                        billbw.write(billstring);
                        billbw.close();
                        
                    }else{
                        int offset = Integer.parseInt(tablemodel.getValueAt(table.getSelectedRow(),0).toString());
                        System.out.print("Tamamlandı: "+ offset+"\n");

                        String line = new String();
                        String lines = new String();
                        for(int x = 0; x<offset;x++){
                            lines =lines + "\n"+basvurubr.readLine();
                        }
                        basvurubr.readLine();
                        while((line = basvurubr.readLine())!=null){
                            lines = lines + "\n"+ line;
                        }
                        lines = lines.trim();
                        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        basvurubr.close();
                        
                        OutputStreamWriter basvurufw = new OutputStreamWriter(new FileOutputStream(basvuruf),StandardCharsets.UTF_8);
                        BufferedWriter basvurubw = new BufferedWriter(basvurufw);
                        basvurubw.write(lines);
                        basvurubw.close();
                    }
                }catch(IOException e){
                    System.err.print(e.getMessage());
                }
                break;
            case 4:
                try{
                    File basvuruf = new File("SignApproval00000.txt");
                    InputStreamReader basvurufr = new InputStreamReader(new FileInputStream(basvuruf),StandardCharsets.UTF_8);
                    BufferedReader basvurubr = new BufferedReader(basvurufr);
                    if(type == true){
                        int offset = Integer.parseInt(tablemodel.getValueAt(table.getSelectedRow(),0).toString());
                        System.out.print("Tamamlandı: "+ offset+"\n");

                        String line = new String();
                        String lines = new String();
                        for(int x = 0; x<offset;x++){
                            lines =lines + "\n"+basvurubr.readLine();
                        }
                        String temp = basvurubr.readLine();
                        String kullanici[] = temp.split(",");
                        while((line = basvurubr.readLine())!=null){
                            lines = lines + "\n"+ line;
                        }
                        lines = lines.trim();
                        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        basvurubr.close();
                        
                        OutputStreamWriter basvurufw = new OutputStreamWriter(new FileOutputStream(basvuruf),StandardCharsets.UTF_8);
                        BufferedWriter basvurubw = new BufferedWriter(basvurufw);
                        basvurubw.write(lines);
                        basvurubw.close();
                        
                        File seizedf = new File("Seized510001.txt");
                        InputStreamReader seizedfr = new InputStreamReader(new FileInputStream(seizedf),StandardCharsets.UTF_8);
                        BufferedReader seizedbr = new BufferedReader(seizedfr);
                        String seizedstring = new String();
                        String tempstring = new String();
                        while((tempstring = seizedbr.readLine())!=null){
                            seizedstring = tempstring;
                            seizedbr.readLine();
                        }
                        String seizedstring2 = seizedstring.replace(" ","").substring(6, 15);
                        seizedstring2 = String.valueOf(Integer.parseInt(seizedstring2)+1);
                        
                        int basamak = seizedstring2.length();
                        for(int x =0;x<9-basamak;x++){
                            seizedstring2 = "0"+seizedstring2;
                        }
                        seizedstring = seizedstring.replace(" ","").replace(seizedstring.replace(" ","").substring(6, 15),seizedstring2);
                        
                        int checkdigit = 0;
                        for(int x =seizedstring.length()-3;x>0;x=x-2){
                            checkdigit = checkdigit + Integer.parseInt(seizedstring.substring(x, x+1));
                        }
                        for(int x =seizedstring.length()-2;x>0;x=x-2){
                            if((Integer.parseInt(seizedstring.substring(x, x+1))*2)>9){
                                checkdigit = checkdigit + ((Integer.parseInt(seizedstring.substring(x, x+1))*2)-9);
                            }else{
                                checkdigit = checkdigit + (Integer.parseInt(seizedstring.substring(x, x+1))*2);
                            }
                        }
                        checkdigit = 10-(checkdigit%10);
                        seizedstring = seizedstring.substring(0, 15)+String.valueOf(checkdigit);
                        seizedstring = seizedstring.substring(0, 4)+" "+seizedstring.substring(4, 8)+" "+seizedstring.substring(8, 12)+" "+seizedstring.substring(12, 16);
                        seizedbr.close();
                        
                        OutputStreamWriter seizedfw = new OutputStreamWriter(new FileOutputStream(seizedf,true),StandardCharsets.UTF_8);
                        BufferedWriter seizedbw = new BufferedWriter(seizedfw);
                        seizedbw.write("\n"+seizedstring+"\n"+";");
                        seizedbw.close();
                        
                        File signedf = new File("Signed00000.txt");
                        InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signedf),StandardCharsets.UTF_8);
                        BufferedReader signedbr = new BufferedReader(signedfr);
                        int where = Integer.parseInt(kullanici[4])-1;
                        String signedlines = new String();
                        for(int x =0;x<where*6;x++){
                            signedlines = signedlines + "\n" + signedbr.readLine();
                        }
                        signedlines = signedlines + "\n" + signedbr.readLine();
                        String signedtemp=new String();
                        if((signedtemp=signedbr.readLine()).equals(";")){
                            signedlines = signedlines + "\nB,"+seizedstring+",2000.00,null,0;";
                        }else{
                            signedlines = signedlines + "\nB,"+seizedstring+",2000.00,null,0;"+signedbr.readLine();
                        }
                        
                        while((signedtemp = signedbr.readLine())!=null){
                            signedlines = signedlines + "\n"+signedtemp;
                        }
                        signedbr.close();
                        
                        OutputStreamWriter signedfw = new OutputStreamWriter(new FileOutputStream(signedf),StandardCharsets.UTF_8);
                        BufferedWriter signedbw = new BufferedWriter(signedfw);
                        signedlines = signedlines.trim();
                        signedlines = signedlines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        signedbw.write(signedlines);
                        signedbw.close();
                        
                    }else{
                        int offset = Integer.parseInt(tablemodel.getValueAt(table.getSelectedRow(),0).toString());
                        System.out.print("Tamamlandı: "+ offset+"\n");

                        String line = new String();
                        String lines = new String();
                        for(int x = 0; x<offset;x++){
                            lines =lines + "\n"+basvurubr.readLine();
                        }
                        basvurubr.readLine();
                        while((line = basvurubr.readLine())!=null){
                            lines = lines + "\n"+ line;
                        }
                        lines = lines.trim();
                        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        basvurubr.close();
                        
                        OutputStreamWriter basvurufw = new OutputStreamWriter(new FileOutputStream(basvuruf),StandardCharsets.UTF_8);
                        BufferedWriter basvurubw = new BufferedWriter(basvurufw);
                        basvurubw.write(lines);
                        basvurubw.close();
                    }
                }catch(IOException e){
                    System.err.print(e.getMessage());
                }
                break;
            default:
                break;
        }
    }
    
    public void krediLimit(String kartno,String limit){
        try{
        File signed = new File("Signed00000.txt");
        InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signed),StandardCharsets.UTF_8);
        BufferedReader signedbr = new BufferedReader(signedfr);
        String lines = new String();
        String line = new String();
        while((line = signedbr.readLine())!=null){
            if(line.contains(kartno)){
                break;
            }else{
               lines = lines +"\n"+ line;
            }
        }
        String temp[] = line.split(";");
        String temptemp[] = null;
        int tempit = 0;
        for(;tempit <temp.length;tempit++){
            if(temp[tempit].contains(kartno)){
                temptemp = temp[tempit].split(",");
                break;
            }
        }
        DecimalFormat dec = new DecimalFormat("#0.00");
        line = line.replace(kartno+","+temptemp[2]+","+temptemp[3],
        kartno+","+dec.format(Double.parseDouble(temptemp[2])).replace(",", ".")+","+dec.format(Double.parseDouble(limit)).replace(",", "."));
        lines = lines+"\n" + line;
        while((line = signedbr.readLine())!=null){
            lines = lines+"\n" + line;
        }
        lines = lines.trim();
        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
        signedbr.close();
        OutputStreamWriter signedfw = new OutputStreamWriter(new FileOutputStream(signed,false),StandardCharsets.UTF_8);
        BufferedWriter signedbw = new BufferedWriter(signedfw);
        signedbw.write(lines);
        signedbw.close();
        
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void pay(String kartno,String aciklama,String degisim){
        hareketekle(kartno,aciklama,"+"+degisim);
        try{
        File signed = new File("Signed00000.txt");
        InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signed),StandardCharsets.UTF_8);
        BufferedReader signedbr = new BufferedReader(signedfr);
        String lines = new String();
        String line = new String();
        while((line = signedbr.readLine())!=null){
            if(line.contains(kartno)){
                break;
            }else{
               lines = lines +"\n"+ line;
            }
        }
        String temp[] = line.split(";");
        String temptemp[] = null;
        int tempit = 0;
        for(;tempit <temp.length;tempit++){
            if(temp[tempit].contains(kartno)){
                temptemp = temp[tempit].split(",");
                break;
            }
        }
        DecimalFormat dec = new DecimalFormat("#0.00");
        line = line.replace(kartno+","+temptemp[2],
        kartno+","+dec.format(Double.parseDouble(temptemp[2])+Double.parseDouble(degisim)).replace(',', '.'));
        lines = lines+"\n" + line;
        while((line = signedbr.readLine())!=null){
            lines = lines+"\n" + line;
        }
        lines = lines.trim();
        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
        signedbr.close();
        OutputStreamWriter signedfw = new OutputStreamWriter(new FileOutputStream(signed,false),StandardCharsets.UTF_8);
        BufferedWriter signedbw = new BufferedWriter(signedfw);
        signedbw.write(lines);
        signedbw.close();
        
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void hareketekle(String kartno,String aciklama, String degisim){
        String filename = null;
        try{
            filename = "Seized"+kartno.substring(0,4)
                            + kartno.substring(5, 7)
                            +".txt";
            File hareket = new File(filename);
            InputStreamReader hareketfr = new InputStreamReader(new FileInputStream(hareket),StandardCharsets.UTF_8);
            BufferedReader hareketbr = new BufferedReader(hareketfr);
            String lines = new String();
            String line = new String();
            while((line=hareketbr.readLine()) !=null){
                lines = lines +"\n"+line;
                if(line.equals(kartno)){break;}
            }
            String theline = hareketbr.readLine();
            if(!theline.equals(";")){
                theline = aciklama+","+degisim+";"+theline;
                lines = lines+"\n"+theline;
            }else{
                theline = aciklama+","+degisim+";";
                lines = lines+"\n"+theline;
            }
            while((line=hareketbr.readLine())!=null){
                lines = lines + "\n"+line;
            }
            lines = lines.trim();
            lines = lines.replaceAll("(?m)^[ \t]*\r?\n", "");
            hareketbr.close();
            OutputStreamWriter hareketfw = new OutputStreamWriter(new FileOutputStream(hareket),StandardCharsets.UTF_8);
            BufferedWriter hareketbw = new BufferedWriter(hareketfw);
            hareketbw.write(lines);
            hareketbw.close();
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void dosyaOku(int iterator){
        try{
            File adminf = new File("Admin000.txt");
            InputStreamReader adminfr = new InputStreamReader(new FileInputStream(adminf),StandardCharsets.UTF_8);
            BufferedReader adminbr = new BufferedReader(adminfr);
            for(int x =0;x<(iterator*6);x++){
                adminbr.readLine();
            }
            hesapid = new String(adminbr.readLine());
            ad = new String(adminbr.readLine());
            soyad = new String(adminbr.readLine());
            stat = new String(adminbr.readLine().split(",")[1]);
            adminbr.close();
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void yerlestirme(int iterator){
        
        int kx,ky;
        kx = p1.getSize().width;
        ky = p1.getSize().height;
        
        //panel 1
            
            JLabel girislabel = new JLabel("Personel olarak giriş yaptınız.");
            girislabel.setBounds(50,60,200,30);
            p1.add(girislabel);
            JLabel person = new JLabel("Personel: "+ad + " "+soyad);
            person.setBounds(50,110,200,30);
            p1.add(person);
            JLabel personid = new JLabel("Personel ID: "+hesapid);
            personid.setBounds(50,160,200,30);
            p1.add(personid);
        
        //panel 2
            
            JLabel basvurulabel = new JLabel("Başvuru Listesi");
            basvurulabel.setFont(basvurulabel.getFont().deriveFont(15f));
            Arayuz.mybounds(basvurulabel, 150, 30, kx, 40);
            p2.add(basvurulabel);
            JTable basvurutable = new JTable();
            DefaultTableModel dtmbasvuru = new DefaultTableModel();
            String dtmbasvuruheader[] = new String[]{"ID","Ad-Soyad","Talep","Neden","Miktar"};
            dtmbasvuru.setColumnIdentifiers(dtmbasvuruheader);
            basvurutable.setModel(dtmbasvuru);
            basvurutable.setRowSelectionAllowed(true);
            basvurutable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            basvurutable.setDefaultEditor(Object.class,null);
            JScrollPane basvurusp = new JScrollPane(basvurutable);
            Arayuz.mybounds(basvurusp, kx-300, ky-150, kx-200, 100);
            p2.add(basvurusp);
            JButton basvurukabul = new JButton("Kabul Et");
            basvurukabul.setSize(100,16);
            basvurukabul.setVisible(false);
            p2.add(basvurukabul);
            JButton basvurured = new JButton("Reddet");
            basvurured.setSize(100,16);
            basvurured.setVisible(false);
            p2.add(basvurured);
            
        //panel 3
            
            JLabel uyelabel = new JLabel("Üye Başvuru Listesi");
            uyelabel.setFont(uyelabel.getFont().deriveFont(15f));
            Arayuz.mybounds(uyelabel, 150, 30, kx, 40);
            p3.add(uyelabel);
            JTable uyetable = new JTable();
            DefaultTableModel dtmuye = new DefaultTableModel();
            String dtmuyeheader[] = new String[]{"ID","Ad","Soyad","Talep"};
            dtmuye.setColumnIdentifiers(dtmuyeheader);
            uyetable.setModel(dtmuye);
            uyetable.setRowSelectionAllowed(true);
            uyetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            uyetable.setDefaultEditor(Object.class,null);
            JScrollPane uyesp = new JScrollPane(uyetable);
            Arayuz.mybounds(uyesp, kx-300, ky-150, kx-200, 100);
            p3.add(uyesp);
            JButton uyekabul = new JButton("Kabul Et");
            uyekabul.setSize(100,16);
            uyekabul.setVisible(false);
            p3.add(uyekabul);
            JButton uyered = new JButton("Reddet");
            uyered.setSize(100,16);
            uyered.setVisible(false);
            p3.add(uyered);
            
        //panel 4
            
            JLabel hesaplabel = new JLabel("Hesap Başvuru Listesi");
            hesaplabel.setFont(hesaplabel.getFont().deriveFont(15f));
            Arayuz.mybounds(hesaplabel, 200, 30, kx, 40);
            p4.add(hesaplabel);
            JTable hesaptable = new JTable();
            DefaultTableModel dtmhesap = new DefaultTableModel();
            String dtmhesapheader[] = new String[]{"ID","Ad","Soyad","Talep"};
            dtmhesap.setColumnIdentifiers(dtmhesapheader);
            hesaptable.setModel(dtmhesap);
            hesaptable.setRowSelectionAllowed(true);
            hesaptable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            hesaptable.setDefaultEditor(Object.class,null);
            JScrollPane hesapsp = new JScrollPane(hesaptable);
            Arayuz.mybounds(hesapsp, kx-300, ky-150, kx-200, 100);
            p4.add(hesapsp);
            JButton hesapkabul = new JButton("Kabul Et");
            hesapkabul.setSize(100,16);
            hesapkabul.setVisible(false);
            p4.add(hesapkabul);
            JButton hesapred = new JButton("Reddet");
            hesapred.setSize(100,16);
            hesapred.setVisible(false);
            p4.add(hesapred);
            
        //panel 5
            
            JLabel musterilabel = new JLabel("Müşteri Ara");
            musterilabel.setFont(musterilabel.getFont().deriveFont(15f));
            Arayuz.mybounds(musterilabel, 150, 30, kx, 40);
            p5.add(musterilabel);
            JLabel musterihesaplabel = new JLabel("Hesap NO:");
            musterihesaplabel.setBounds(50,160,100,30);
            p5.add(musterihesaplabel);
            JTextField musterihesap = new JTextField();
            musterihesap.setBounds(150,160,150,30);
            p5.add(musterihesap);
            JLabel musteriIDlabel = new JLabel("ID:");
            musteriIDlabel.setBounds(50,210,100,30);
            p5.add(musteriIDlabel);
            JTextField musteriID = new JTextField();
            musteriID.setBounds(150,210,150,30);
            p5.add(musteriID);
            JLabel musteriadlabel = new JLabel("Ad:");
            musteriadlabel.setBounds(50,260,100,30);
            p5.add(musteriadlabel);
            JTextField musteriad = new JTextField();
            musteriad.setBounds(150,260,150,30);
            p5.add(musteriad);
            JLabel musterisoyadlabel = new JLabel("Soyad:");
            musterisoyadlabel.setBounds(50,310,100,30);
            p5.add(musterisoyadlabel);
            JTextField musterisoyad = new JTextField();
            musterisoyad.setBounds(150,310,150,30);
            p5.add(musterisoyad);
            JButton musteriara = new JButton("ARA");
            musteriara.setFont(musteriara.getFont().deriveFont(15f));
            musteriara.setBounds(150,360,150,50);
            p5.add(musteriara);
            JTable musteritable = new JTable();
            DefaultTableModel dtmmusteri = new DefaultTableModel();
            String dtmmusteriheader[] = new String[]{"ID","Ad","Soyad","Hesap"};
            dtmmusteri.setColumnIdentifiers(dtmmusteriheader);
            musteritable.setModel(dtmmusteri);
            musteritable.setRowSelectionAllowed(true);
            musteritable.setDefaultEditor(Object.class,null);
            JScrollPane musterisp = new JScrollPane(musteritable);
            Arayuz.mybounds(musterisp, kx/2+50, ky-150, kx+350, 100);
            p5.add(musterisp);
            
        //panel 6
        
//            JLabel messagelabel = new JLabel("Destek ve Yardım Hattı");
//            Arayuz.mybounds(messagelabel, 200, 30, kx, 50);
//            messagelabel.setFont(messagelabel.getFont().deriveFont(15f));
//            messagelabel.setHorizontalTextPosition(JLabel.CENTER);
//            p6.add(messagelabel);
//            Vector<JTextArea> messageclient = new Vector();
//            Vector<JTextArea> messagesupport = new Vector();
//            JTextArea chat = new JTextArea();
//            chat.setBounds(50,ky-90,kx-200,70);
//            chat.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
//            JButton send = new JButton("Gönder");
//            send.setBounds(kx-145,ky-90,100,50);
//            SpringLayout spring = new SpringLayout();
//            ScrollablePanel mainpanel = new ScrollablePanel();
//            mainpanel.setLayout(new BoxLayout(mainpanel,BoxLayout.X_AXIS));
//            ScrollablePanel messagepanel2 = new ScrollablePanel();
//            messagepanel2.setLayout(new BoxLayout(messagepanel2,BoxLayout.PAGE_AXIS));
//            ScrollablePanel messagepanel = new ScrollablePanel();
//            messagepanel.setLayout(new BoxLayout(messagepanel,BoxLayout.PAGE_AXIS));
//            mainpanel.add(messagepanel);
//            mainpanel.add(messagepanel2);
//            JScrollPane messagesp = new JScrollPane(mainpanel);
//            p6.add(chat);
//            p6.add(send);
//            Arayuz.mybounds(messagesp,kx-100,ky-200,kx,100);
//            p6.add(messagesp);
//            JButton messagerefresh = new JButton("Yenile");
//            messagerefresh.setBounds(50,50,80,50);
//            p6.add(messagerefresh);
//            messagesp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//            
//            send.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                
//                String message = new String(chat.getText());
//                messageclient.add(new JTextArea(message));
//                messageclient.add(new JTextArea(message));
//                messageclient.lastElement().setLineWrap(true);
//                messageclient.lastElement().setWrapStyleWord(true);
//                messageclient.lastElement().setBorder(BorderFactory.createLineBorder(Color.blue,2));
//                messageclient.elementAt(messageclient.size()-2).setBorder(BorderFactory.createLineBorder(Color.red,2));
//                messageclient.elementAt(messageclient.size()-2).setLineWrap(true);
//                messageclient.elementAt(messageclient.size()-2).setWrapStyleWord(true);
//                messagepanel.add(messageclient.lastElement());
//                messagepanel2.add(messageclient.elementAt(messageclient.size()-2));
//                messagesp.setVisible(false);
//                messagesp.setVisible(true);
//            }});
            
        //panel 7
        
            JButton hesapbuton = new JButton("Parola Değiştir");
            Arayuz.mybounds(hesapbuton,150,30,kx,300);
            p7.add(hesapbuton);
            JPasswordField hesapyenisifre = new JPasswordField();
            Arayuz.mybounds(hesapyenisifre,150,30,kx,150);
            p7.add(hesapyenisifre);
            JPasswordField hesaponaysifre = new JPasswordField();
            Arayuz.mybounds(hesaponaysifre,150,30,kx,200);
            p7.add(hesaponaysifre);
            JPasswordField hesapeskisifre = new JPasswordField();
            Arayuz.mybounds(hesapeskisifre,150,30,kx,250);
            p7.add(hesapeskisifre);
            JLabel hesaplabel1 = new JLabel("Yeni Parola: ");
            Arayuz.mybounds(hesaplabel1,150 , 30,kx-200, 150);
            p7.add(hesaplabel1);
            JLabel hesaplabel2 = new JLabel("Yeni Parola Onay: ");
            Arayuz.mybounds(hesaplabel2,150,30 ,kx-200, 200);
            p7.add(hesaplabel2);
            JLabel hesaplabel3 = new JLabel("Eski Parola");
            Arayuz.mybounds(hesaplabel3,150,30, kx-200, 250);
            p7.add(hesaplabel3);
            
        //exit
            
            oturumkapat.setFont(oturumkapat.getFont().deriveFont(15f));
            oturumkapat.setBackground(Color.red);
            oturumkapat.setForeground(Color.white);
            Arayuz.mybounds(oturumkapat,150,40,kx,ky/2-50);
            exit.add(oturumkapat);
            JLabel izin = new JLabel("Oturumu kapatmak istediğinizden emin misiniz?");
            izin.setFont(izin.getFont().deriveFont(15f));
            Arayuz.mybounds(izin,350,30,kx,ky/2-100);
            exit.add(izin);
            
        //listener field
        tp.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent gecis){
                if(tp.getSelectedIndex()==0){
                    
                }
                if(tp.getSelectedIndex()==1){
                    tablefunc(dtmbasvuru,2);
                    hesapkabul.setVisible(false);
                    hesapred.setVisible(false);
                    uyekabul.setVisible(false);
                    uyered.setVisible(false);
                }
                if(tp.getSelectedIndex()==2){
                    tablefunc(dtmuye,3);
                    hesapkabul.setVisible(false);
                    hesapred.setVisible(false);
                    basvurukabul.setVisible(false);
                    basvurured.setVisible(false);
                }
                if(tp.getSelectedIndex()==3){
                    tablefunc(dtmhesap,4);
                    uyekabul.setVisible(false);
                    uyered.setVisible(false);
                    basvurukabul.setVisible(false);
                    basvurured.setVisible(false);
                }
                if(tp.getSelectedIndex()==4){
                    
                }
                if(tp.getSelectedIndex()==5){
                   
                }
                if(tp.getSelectedIndex()==6){
                    
                }
                if(tp.getSelectedIndex()==7){
                    
                }
            }
        });
        
        musteriara.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String kullanici_ad = musteriad.getText();
                if(kullanici_ad.contains(" ")&&kullanici_ad.length()>2){
                    kullanici_ad = kullanici_ad.split(" ")[0].substring(0,1).toUpperCase()+kullanici_ad.split(" ")[0].substring(1).toLowerCase()+" "+kullanici_ad.split(" ")[1].substring(0, 1).toUpperCase()+kullanici_ad.split(" ")[1].substring(1).toLowerCase();
                }else if(kullanici_ad.length()>0){
                    kullanici_ad = kullanici_ad.substring(0, 1).toUpperCase()+kullanici_ad.substring(1).toLowerCase();
                }
                String kullanici_soyad = musterisoyad.getText();
                kullanici_soyad = kullanici_soyad.toUpperCase();
                String kullanici_id = musteriID.getText();
                String kullanici_kart = musterihesap.getText();
                
                String tempad = new String();
                String tempsoyad = new String();
                String tempid = new String();
                String tempkart[];
                String temp = new String();
                
                for(int x = dtmmusteri.getRowCount()-1;x>=0;x--){
                    dtmmusteri.removeRow(x);
                }
                try{
                    File signedf = new File("Signed00000.txt");
                    InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signedf),StandardCharsets.UTF_8);
                    BufferedReader signedbr = new BufferedReader(signedfr);
                    
                    while((tempid = signedbr.readLine())!=null){
                        temp = signedbr.readLine();
                        tempkart = temp.split(";");
                        for(int x = 0;x<tempkart.length;x++){
                            tempkart[x] = tempkart[x].split(",")[1];
                        }
                        tempad = signedbr.readLine();
                        tempsoyad = signedbr.readLine();
                        signedbr.readLine();
                        signedbr.readLine();
                        System.out.print(tempid+"\n"+tempad+"\n"+tempsoyad+"\n"+tempkart.toString());
                        
                        if((!tempid.equals("")&&tempid.contains(kullanici_id))||((tempid.equals(""))&&!tempid.contains(kullanici_id))){
                            if((!tempad.equals("")&&tempad.contains(kullanici_ad))||((tempad.equals(""))&&!tempad.contains(kullanici_ad))){
                                if((!tempsoyad.equals("")&&tempsoyad.contains(kullanici_soyad))||((tempsoyad.equals(""))&&!tempsoyad.contains(kullanici_soyad))){
                                    if(tempkart.length>0){
                                        for(String x : tempkart){
                                            if((!tempkart.equals("")&&x.contains(kullanici_kart))||((tempkart.equals(""))&&!x.contains(kullanici_kart))){
                                                dtmmusteri.addRow(new String[]{tempid,tempad,tempsoyad,x});
                                            }
                                        }
                                    }else{
                                        dtmmusteri.addRow(new String[]{tempid,tempad,tempsoyad,"-"});
                                    }
                                }
                            }
                        }
                        
                    }
                    signedbr.close();
                }catch(IOException ex){
                    System.err.print(ex.getMessage());
                }
            }
        });
        
        basvurutable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                if(basvurutable.getSelectedRow()!=-1){
                    iterate=basvurutable.getSelectedRow();
                    System.out.print("Seçilen satır: "+iterate+ "\n");
                    int y = 100+((basvurutable.getSelectedRow()+1)*basvurutable.getRowHeight()),x=basvurusp.getLocation().x+basvurusp.getSize().width;
                    basvurukabul.setLocation(x+10,y);
                    basvurured.setLocation(x+120, y);
                    basvurukabul.setVisible(true);
                    basvurured.setVisible(true);
                }
        }});
            
        basvurukabul.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tableUpdate(true,basvurutable,dtmbasvuru,2);
                tablefunc(dtmbasvuru,2);
                basvurured.setVisible(false);
                basvurukabul.setVisible(false);
                
        }});
            
        basvurured.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tableUpdate(false,basvurutable,dtmbasvuru,2);
                tablefunc(dtmbasvuru,2);
                basvurured.setVisible(false);
                basvurukabul.setVisible(false);
        }});
        
        hesaptable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                if(hesaptable.getSelectedRow()!=-1){
                    iterate=hesaptable.getSelectedRow();
                    System.out.print("Seçilen satır: "+iterate+ "\n");
                    int y = 100+((hesaptable.getSelectedRow()+1)*hesaptable.getRowHeight()),x=hesapsp.getLocation().x+hesapsp.getSize().width;
                    hesapkabul.setLocation(x+10,y);
                    hesapred.setLocation(x+120, y);
                    hesapkabul.setVisible(true);
                    hesapred.setVisible(true);
                }
        }});
            
        hesapkabul.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tableUpdate(true,hesaptable,dtmhesap,4);
                tablefunc(dtmhesap,4);
                hesapred.setVisible(false);
                hesapkabul.setVisible(false);
        }});
            
        hesapred.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tableUpdate(false,hesaptable,dtmhesap,4);
                tablefunc(dtmhesap,4);
                hesapred.setVisible(false);
                hesapkabul.setVisible(false);
        }});
        
        uyetable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                if(uyetable.getSelectedRow()!=-1){
                    iterate=uyetable.getSelectedRow();
                    System.out.print("Seçilen satır: "+iterate+ "\n");
                    int y = 100+((uyetable.getSelectedRow()+1)*uyetable.getRowHeight()),x=uyesp.getLocation().x+uyesp.getSize().width;
                    uyekabul.setLocation(x+10,y);
                    uyered.setLocation(x+120, y);
                    uyekabul.setVisible(true);
                    uyered.setVisible(true);
                }
        }});
            
        uyekabul.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tableUpdate(true,uyetable,dtmuye,3);
                tablefunc(dtmuye,3);
                uyered.setVisible(false);
                uyekabul.setVisible(false);
        }});
            
        uyered.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tableUpdate(false,uyetable,dtmuye,3);
                tablefunc(dtmuye,3);
                uyered.setVisible(false);
                uyekabul.setVisible(false);
        }});
        
        hesapbuton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String yeni = new String(hesapyenisifre.getPassword());
                String onay = new String(hesaponaysifre.getPassword());
                String eski = new String(hesapeskisifre.getPassword());
                if(yeni.equals(onay)&& !yeni.isEmpty() && !eski.isEmpty()){
                    String eskisifre = null;
                    try{
                        File adminf = new File("Admin000.txt");
                        InputStreamReader adminfr = new InputStreamReader(new FileInputStream(adminf),StandardCharsets.UTF_8);
                        BufferedReader adminbr = new BufferedReader(adminfr);
                        for(int x = 0;x<(iterator*6);x++){
                            adminbr.readLine();
                        }
                        adminbr.readLine();
                        adminbr.readLine();
                        adminbr.readLine();
                        adminbr.readLine();
                        eskisifre = adminbr.readLine();
                        adminbr.close();
                        }catch(IOException ex){
                            System.out.print(ex.getMessage());
                    }
                    if(eski.equals(eskisifre)){
                        try{
                        File adminf = new File("Admin000.txt");
                        InputStreamReader adminfr = new InputStreamReader(new FileInputStream(adminf),StandardCharsets.UTF_8);
                        BufferedReader adminbr = new BufferedReader(adminfr);
                        String line = new String();
                        String lines = new String();
                        for(int x = 0;x<(iterator*6);x++){
                            lines = lines + "\n"+adminbr.readLine();
                        }
                        lines = lines + "\n" +adminbr.readLine();
                        lines = lines + "\n" +adminbr.readLine();
                        lines = lines + "\n" +adminbr.readLine();
                        lines = lines + "\n" +adminbr.readLine();
                        adminbr.readLine();
                        lines = lines + "\n"+yeni;
                        while((line = adminbr.readLine())!=null){
                            lines = lines + "\n"+line;
                        }
                        lines = lines.trim();
                        lines = lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        adminbr.close();
                        OutputStreamWriter adminfw = new OutputStreamWriter(new FileOutputStream(adminf),StandardCharsets.UTF_8);
                        BufferedWriter adminbw = new BufferedWriter(adminfw);
                        adminbw.write(lines);
                        adminbw.close();
                        }catch(IOException ex){
                            System.out.print(ex.getMessage());
                    }
                        JOptionPane.showMessageDialog(new JFrame(),"İşlem Başarılı","BAŞARILI",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(new JFrame(),"Bilgileri Eksik Veya Yanlış Girdiniz","UYARI",JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"Bilgileri Eksik Veya Yanlış Girdiniz","UYARI",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        oturumkapat.addActionListener(this);
    }
    
    public void giriliyor(){
        
        pframe.add(tp);
        pframe.setResizable(false);
        pframe.setSize(1000,700);
        frame.setVisible(false);
        pframe.setLocation(200,200);
        pframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        tp.addTab("Personel",null,p1,"Hesap bilgileri");
        tp.addTab("Başvuru Kabul",null,p2,"Başvuru onay işlemleri");
        tp.addTab("Üye Kabul",null,p3,"Üye işlemlerini onayla");
        tp.addTab("Hesap Kabul",null,p4,"Hesap açma işlemlerini onayla");
        tp.addTab("Müşteri Ara",null,p5,"Müşteri arama");
        //tp.addTab("Destek",null,p6,"Müşteri destek hattı");
        tp.addTab("Hesap Ayarları",null,p7,"Hesap bilgilerinizi değiştirin");
        tp.addTab("Oturumu Kapat",null,exit,null);
        
        tp.setBounds(0,0,1000,700);
        tp.setBackground(Color.lightGray);
        tp.setForeground(Color.green);
        tp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tp.setOpaque(true);
        
        pframe.setVisible(true);
        pframe.setLayout(null);
        
        {
            p1.setBorder(BorderFactory.createLineBorder(Color.lightGray, 10));
            p1.setBackground(Color.WHITE);
            p1.setLayout(null);
            p2.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p2.setBackground(Color.WHITE);
            p2.setLayout(null);
            p3.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p3.setBackground(Color.WHITE);
            p3.setLayout(null);
            p4.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p4.setBackground(Color.WHITE);
            p4.setLayout(null);
            p5.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p5.setBackground(Color.WHITE);
            p5.setLayout(null);
            p6.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p6.setBackground(Color.WHITE);
            p6.setLayout(null);
            p7.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p7.setBackground(Color.WHITE);
            p7.setLayout(null);
            exit.setBackground(Color.WHITE);
            exit.setLayout(null);
            for(int x=0;x<6;x++){
                tp.setBackgroundAt(x, new Color(128,128,128));
                tp.setMnemonicAt(x, KeyEvent.VK_0);
            }
            tp.setBackgroundAt(6, Color.red);
            tp.setForegroundAt(6, Color.white);
        }
    }
    
}

class Kullanici extends Kul{
    Kullanici(int iterator){
        giriliyor();
        yerlestirme(iterator);
        dosyaOku(iterator);
        kartlarfunc();
    }
    
    private ListCellRenderer<? super String> getRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,cellHasFocus);
                listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
                return listCellRendererComponent;
            }
        };
    }
    
    public void hesaptxtp5update(){
        hesaptxtp5.setText("");
                for(Vector<String> x: kartlar){
                    if(x.elementAt(3).equals("null")){
                        hesaptxtp5.setText(hesaptxtp5.getText()+ad+" "+soyad+ "\nKart No: "+
                        x.elementAt(1)+
                        "\nBakiye: "+x.elementAt(2)
                        + " TL\n\n");
                    }else{
                        hesaptxtp5.setText(hesaptxtp5.getText()+ad+" "+soyad+ "\nKart No: "+
                        x.elementAt(1)+
                        "\nToplam Limit: "+ x.elementAt(3)
                        +" TL\nKullanılabilir: "+x.elementAt(2)
                        +" TL\n\n");
                    }
                }
    }
    
    public void kartlarfunc(){
        for(Vector x: kartlar){
            if(x.elementAt(0).equals("B")){
                hesaplistmodel.addElement("Banka: " + x.elementAt(1));
            }else{
                hesaplistmodel.addElement("Kredi: " + x.elementAt(1));
            }
        }
    }
    
    public void tablefunc(DefaultTableModel dtm, int index){
        File gettable = null;
        InputStreamReader gettablefr = null;
        BufferedReader gettablebr = null;
        String filename = null;
        String checker = null;
        try{
            switch (index) {
                case 1:
                    for(int x = dtm.getRowCount()-1;x>=0;x--){
                        dtm.removeRow(x);
                    }
                    filename = "Seized"+kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(1).substring(0,4)
                            + kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(1).substring(5, 7)
                            +".txt";
                    gettable = new File(filename);
                    gettablefr = new InputStreamReader(new FileInputStream(gettable),StandardCharsets.UTF_8);
                    gettablebr = new BufferedReader(gettablefr);
                    for(;;){
                        if(gettablebr.readLine().equals(kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(1))){break;}
                    }
                    checker = gettablebr.readLine();
                    if(checker.equals(";")){break;}
                    String gecmis[] = checker.split(";");
                    for(String x: gecmis){
                        dtm.addRow(x.split(","));
                    }
                    gettablebr.close();
                    break;
                case 3:
                    for(int x = dtm.getRowCount()-1;x>=0;x--){
                        dtm.removeRow(x);
                    }
                    for(Vector<String> x: faturalar){
                        if(odemecombo.getSelectedIndex()==1){
                            if(x.elementAt(0).equals("E")){
                                dtm.addRow(new String[]{x.elementAt(2),"Elektrik Bedeli",x.elementAt(1)});
                            }
                        }else if(odemecombo.getSelectedIndex()==2){
                            if(x.elementAt(0).equals("D")){
                                dtm.addRow(new String[]{x.elementAt(2),"Doğalgaz Bedeli",x.elementAt(1)});
                            }
                        }else if(odemecombo.getSelectedIndex()==3){
                            if(x.elementAt(0).equals("S")){
                                dtm.addRow(new String[]{x.elementAt(2),"Su Bedeli",x.elementAt(1)});
                            }
                        }else if(odemecombo.getSelectedIndex()==4){
                            if(x.elementAt(0).equals("TV")){
                                dtm.addRow(new String[]{x.elementAt(2),"Televizyon Bedeli",x.elementAt(1)});
                            }
                        }else if(odemecombo.getSelectedIndex()==5){
                            if(x.elementAt(0).equals("T")){
                                dtm.addRow(new String[]{x.elementAt(2),"GSM Bedeli",x.elementAt(1)});
                            }
                        }else if(odemecombo.getSelectedIndex()==6){
                            if(x.elementAt(0).equals("I")){
                                dtm.addRow(new String[]{x.elementAt(2),"Internet Bedeli",x.elementAt(1)});
                            }
                        }else if(odemecombo.getSelectedIndex()==7){
                            if(x.elementAt(0).equals("KV")){
                                dtm.addRow(new String[]{x.elementAt(2),"Konut Vergisi",x.elementAt(1)});
                            }
                        }
                    }
                    break;
                case 5:
                    for(int x = dtm.getRowCount()-1;x>=0;x--){
                        dtm.removeRow(x);
                    }
                    for(Vector<String> x: kartlar){
                        gettable = null;
                        gettablefr = null;
                        gettablebr = null;
                        filename = "Seized"+x.elementAt(1).substring(0,4)
                                + x.elementAt(1).substring(5, 7)
                                +".txt";
                        gettable = new File(filename);
                        gettablefr = new InputStreamReader(new FileInputStream(gettable),StandardCharsets.UTF_8);
                        gettablebr = new BufferedReader(gettablefr);
                        for(;;){
                            if(gettablebr.readLine().equals(x.elementAt(1))){break;}
                        }
                        checker = gettablebr.readLine();
                        if(checker.equals(";")){break;}
                        String gecmiss[] = checker.split(";");
                        for(String y: gecmiss){
                            String temp[]=new String[3];
                            if(x.elementAt(0).equals("B")){
                                temp[0]="Banka: "+x.elementAt(1);
                            }else{
                                temp[0]="Kredi: "+x.elementAt(1);
                            }
                            temp[1]=y.split(",")[0];
                            temp[2]=y.split(",")[1];
                            dtm.addRow(temp);
                        }
                        gettablebr.close();
                    }
                    break;
                default:
                    break;
        }
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void dosyaOku(int iterator){
        try{
            File signed = new File("Signed00000.txt");
            InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signed),StandardCharsets.UTF_8);
            BufferedReader signedbr = new BufferedReader(signedfr);
            for(int x = 0;x<(iterator*6);x++){
                signedbr.readLine();
            }
            hesapid = signedbr.readLine();
            String tempstring[] = signedbr.readLine().split(";");
            kartlar = new Vector<>();
            for(int x =0 ;x<tempstring.length;x++){ 
                kartlar.add(new Vector<>());
                String temp[] = tempstring[x].split(",");
                for(int y =0;y<temp.length;y++){
                    kartlar.elementAt(x).add(temp[y]);
                }
            }
            ad = signedbr.readLine();
            soyad = signedbr.readLine();
            signedbr.close();
            
            File bills = new File("Bills00000.txt");
            InputStreamReader billsfr = new InputStreamReader(new FileInputStream(bills),StandardCharsets.UTF_8);
            BufferedReader billsbr = new BufferedReader(billsfr);
            for(int x =0; x<(iterator*3)+1;x++){
                billsbr.readLine();
            }
            tempstring = billsbr.readLine().split(";");
            faturalar = new Vector<>();
            for(int x=0;x<tempstring.length;x++){
                faturalar.add(new Vector<>());
                String temp[] = tempstring[x].split(",");
                for(int y =0;y<temp.length;y++){
                    faturalar.elementAt(x).add(temp[y]);
                }
            }
            billsbr.close();
            
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void paratransfer(boolean type,String iban,String transfer){
        try{
            if(type){
                File signed = new File("Signed00000.txt");
                InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signed),StandardCharsets.UTF_8);
                BufferedReader signedbr = new BufferedReader(signedfr);
                String lines = new String();
                String line = new String();
                while((line = signedbr.readLine())!=null){
                    if(line.equals(hesapid)){
                        lines = lines+"\n"+line;
                        break;
                    }else{
                       lines = lines +"\n"+ line;
                       lines = lines+"\n" +signedbr.readLine();
                       lines = lines+"\n" +signedbr.readLine();
                       lines = lines+"\n" +signedbr.readLine();
                       lines = lines+"\n" +signedbr.readLine();
                       lines = lines+"\n" +signedbr.readLine();
                    }
                }
                line = signedbr.readLine();
                DecimalFormat dec = new DecimalFormat("#0.00");
                line = line.replace(kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(1)+","+kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(2),
                        kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(1)+","+dec.format(Double.parseDouble(kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(2))-Double.parseDouble(transfer)).replace(',', '.'));
                line = line.replace(kartlar.elementAt(hesaplistcopy.getSelectedIndex()).elementAt(1)+","+kartlar.elementAt(hesaplistcopy.getSelectedIndex()).elementAt(2),
                        kartlar.elementAt(hesaplistcopy.getSelectedIndex()).elementAt(1)+","+dec.format(Double.parseDouble(kartlar.elementAt(hesaplistcopy.getSelectedIndex()).elementAt(2))+Double.parseDouble(transfer)).replace(',', '.'));
                lines = lines+"\n" + line;
                while((line = signedbr.readLine())!=null){
                    lines = lines+"\n" + line;
                }
                lines = lines.trim();
                lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                signedbr.close();
                OutputStreamWriter signedfw = new OutputStreamWriter(new FileOutputStream(signed,false),StandardCharsets.UTF_8);
                BufferedWriter signedbw = new BufferedWriter(signedfw);
                signedbw.write(lines);
                signedbw.close();
                hareketekle(kartlar.elementAt(hesaplistcopy.getSelectedIndex()).elementAt(1).toString(),"Para Transferi","+"+transfer);
                hareketekle(kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(1).toString(),"Para Transferi","-"+transfer);
                
            }else{
                pay("Para Transferi",transfer);
            }
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void pay(String aciklama,String degisim){
        int select = hesaplist.getSelectedIndex();
        hareketekle(kartlar.elementAt(select).elementAt(1),aciklama,"-"+degisim);
        try{
        File signed = new File("Signed00000.txt");
        InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signed),StandardCharsets.UTF_8);
        BufferedReader signedbr = new BufferedReader(signedfr);
        String lines = new String();
        String line = new String();
        while((line = signedbr.readLine())!=null){
            if(line.equals(hesapid)){
                lines = lines+"\n"+line;
                break;
            }else{
               lines = lines +"\n"+ line;
               lines = lines+"\n" +signedbr.readLine();
               lines = lines+"\n" +signedbr.readLine();
               lines = lines+"\n" +signedbr.readLine();
               lines = lines+"\n" +signedbr.readLine();
               lines = lines+"\n" +signedbr.readLine();
            }
        }
        line = signedbr.readLine();
        DecimalFormat dec = new DecimalFormat("#0.00");
        line = line.replace(kartlar.elementAt(select).elementAt(1)+","+kartlar.elementAt(select).elementAt(2),
        kartlar.elementAt(select).elementAt(1)+","+dec.format(Double.parseDouble(kartlar.elementAt(select).elementAt(2))-Double.parseDouble(degisim)).replace(',', '.'));
        lines = lines+"\n" + line;
        while((line = signedbr.readLine())!=null){
            lines = lines+"\n" + line;
        }
        lines = lines.trim();
        lines =lines.replaceAll("(?m)^[ \t]*\r?\n", "");
        signedbr.close();
        OutputStreamWriter signedfw = new OutputStreamWriter(new FileOutputStream(signed,false),StandardCharsets.UTF_8);
        BufferedWriter signedbw = new BufferedWriter(signedfw);
        signedbw.write(lines);
        signedbw.close();
        
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void hareketekle(String kartno,String aciklama, String degisim){
        String filename = null;
        try{
            filename = "Seized"+kartno.substring(0,4)
                            + kartno.substring(5, 7)
                            +".txt";
            File hareket = new File(filename);
            InputStreamReader hareketfr = new InputStreamReader(new FileInputStream(hareket),StandardCharsets.UTF_8);
            BufferedReader hareketbr = new BufferedReader(hareketfr);
            String lines = new String();
            String line = new String();
            while((line=hareketbr.readLine()) !=null){
                lines = lines +"\n"+line;
                if(line.equals(kartno)){break;}
            }
            String theline = hareketbr.readLine();
            if(!theline.equals(";")){
                theline = aciklama+","+degisim+";"+theline;
                lines = lines+"\n"+theline;
            }else{
                theline = aciklama+","+degisim+";";
                lines = lines+"\n"+theline;
            }
            while((line=hareketbr.readLine())!=null){
                lines = lines + "\n"+line;
            }
            lines = lines.trim();
            lines = lines.replaceAll("(?m)^[ \t]*\r?\n", "");
            hareketbr.close();
            OutputStreamWriter hareketfw = new OutputStreamWriter(new FileOutputStream(hareket),StandardCharsets.UTF_8);
            BufferedWriter hareketbw = new BufferedWriter(hareketfw);
            hareketbw.write(lines);
            hareketbw.close();
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public void yerlestirme(int iterator){
        
        hesaptxt.setEditable(false);
        hesaptxtp2.setEditable(false);
        hesaptxtp5.setEditable(false);
        
        int kx,ky;
        kx = p1.getSize().width;
        ky = p1.getSize().height; 
        
        //panel 1
            
            JLabel hesaplar = new JLabel("HESAPLARINIZ");
            hesaplar.setFont(hesaplar.getFont().deriveFont(20f));
            Arayuz.mybounds(hesaplar,150,50,kx,30);
            p1.add(hesaplar);
            JButton hareket = new JButton("Kart/Hesap Hareketleri");
            Arayuz.mybounds(hareket,200,40,kx+500,ky-150);
            p1.add(hareket);
            JButton hesap = new JButton("Hesap Özeti");
            Arayuz.mybounds(hesap,150,40,kx+100,ky-150);
            p1.add(hesap);
            hesaptxt.setBorder(BorderFactory.createLineBorder(Color.blue,2));
            Arayuz.mybounds(hesaptxt,kx/2,ky/2,kx+300,100);
            p1.add(hesaptxt);
            p1.add(hesaplist);
            Arayuz.mybounds(hesaplist,kx/3,ky/2,kx-500,100);
            hesaplist.setBorder(BorderFactory.createLineBorder(Color.blue,1));
            hesaplist.setCellRenderer(getRenderer());
            JTable hesaphareket = new JTable();
            JScrollPane hareketsp= new JScrollPane(hesaphareket);
            Arayuz.mybounds(hareketsp,kx/2,ky/2,kx+300,100);
            p1.add(hareketsp);
            hareketsp.setVisible(false);
            DefaultTableModel dtmp1 = new DefaultTableModel(0,0);
            String hesaphareketheader[]= new String[]{"Açıklama","Değişim"};
            dtmp1.setColumnIdentifiers(hesaphareketheader);
            hesaphareket.setRowSelectionAllowed(true);
            hesaphareket.setDefaultEditor(Object.class, null);
            hesaphareket.setModel(dtmp1);
            
        
        
        //panel 2
            hesaptxtp2.setBorder(hesaptxt.getBorder());
            p2.add(hesaptxtp2);
            hesaplistcopy.setBorder(hesaplist.getBorder());
            Arayuz.mybounds(hesaplistcopy,kx/3,ky/2,kx+500,100);
            hesaplistcopy.setCellRenderer(hesaplist.getCellRenderer());
            p2.add(hesaplistcopy);
            JButton mytransfer=new JButton("Kendi Hesaplarım Arasında");
            Arayuz.mybounds(mytransfer,200,40,kx-230,40);
            p2.add(mytransfer);
            JButton ourtransfer=new JButton("Başka Bir Hesaba");
            Arayuz.mybounds(ourtransfer,150,40,kx+180,40);
            p2.add(ourtransfer);
            JButton transfer = new JButton("GÖNDER >>>");
            Arayuz.mybounds(transfer,150,40,kx,ky-100);
            p2.add(transfer);
            JLabel p2label = new JLabel("Miktar:");
            Arayuz.mybounds(p2label,50,40,kx,ky/3+20);
            p2.add(p2label);
            JTextField tto = new JTextField();
            Arayuz.mybounds(tto,200,40,kx,ky/3+60);
            tto.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
            p2.add(tto);
            JLabel ltto = new JLabel("Gönderilecek IBAN:");
            Arayuz.mybounds(ltto,200,30,kx+500,ky/3-50);
            p2.add(ltto);
            JTextField totto = new JTextField();
            Arayuz.mybounds(totto,200,40,kx+500,ky/3);
            p2.add(totto);
            JLabel ltme = new JLabel("Hesap seçimi");
            Arayuz.mybounds(ltme,100,30,kx,ky/3-50);
            p2.add(ltme);
            JLabel okisareti = new JLabel(">>>>>>>>>>>");
            Arayuz.mybounds(okisareti,100,20,kx,ky/3);
            p2.add(okisareti);
            tto.setHorizontalAlignment(SwingConstants.RIGHT);
            
            
        //panel 3
            JButton odeme = new JButton("Ödemeyi Onayla");
            Arayuz.mybounds(odeme,200,40,kx,ky-100);
            p3.add(odeme);
            Arayuz.mybounds(odemecombo,200,40,kx,80);
            p3.add(odemecombo);
            JTable odemetable = new JTable();
            DefaultTableModel dtmp3 = new DefaultTableModel(0,0);
            String odemeheader[]= new String[]{"No","Açıklama","Tutar"};
            dtmp3.setColumnIdentifiers(odemeheader);
            odemetable.setModel(dtmp3);
            odemetable.setRowSelectionAllowed(true);
            odemetable.setDefaultEditor(Object.class, null);
            odemetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane odemesp = new JScrollPane(odemetable);
            Arayuz.mybounds(odemesp,kx/3,ky/2,320,175);
            p3.add(odemesp);
            JLabel odemelabel = new JLabel("Ödeme Türünü ve Yöntemini Seçiniz");
            odemelabel.setFont(odemelabel.getFont().deriveFont(15f));
            Arayuz.mybounds(odemelabel,275,30,kx,30);
            p3.add(odemelabel);
            
        //panel 4
            JLabel p4label = new JLabel("Kredi Başvuru Formunu Doldurunuz");
            p4label.setFont(p4label.getFont().deriveFont(15f));
            Arayuz.mybounds(p4label,275,30,kx,30);
            p4.add(p4label);
            JButton kredibuton = new JButton("BAŞVUR");
            Arayuz.mybounds(kredibuton,100,40,kx,ky-80);
            p4.add(kredibuton);
            JTextField kredimiktarcvp = new JTextField();
            Arayuz.mybounds(kredimiktarcvp,100,40,kx-500,160);
            p4.add(kredimiktarcvp);
            JLabel kredimiktar = new JLabel("Miktar:");
            Arayuz.mybounds(kredimiktar,50,30,kx-750,160);
            p4.add(kredimiktar);
            JLabel kredisecim = new JLabel("Hesap:");
            kredisecim.setFont(kredisecim.getFont().deriveFont(15f));
            Arayuz.mybounds(kredisecim,50,30,kx,100);
            p4.add(kredisecim);
            JLabel kredineden = new JLabel("Hangi amaç için:");
            Arayuz.mybounds(kredineden,100,30,kx-700,100);
            p4.add(kredineden);
            String nedenler[] = {"Seçiniz","İhtiyaç","Konut","Ticari","Taşıt","Kurumsal"};
            JComboBox kredinedencvp = new JComboBox(nedenler);
            Arayuz.mybounds(kredinedencvp,100,40,kx-500,100);
            p4.add(kredinedencvp);
            kredimiktarcvp.setHorizontalAlignment(SwingConstants.RIGHT);
        
        //panel 5
            JLabel varlık = new JLabel("Sahip Olduğunuz Tüm Varlıklar");
            Arayuz.mybounds(varlık,250,30,kx,30);
            varlık.setFont(varlık.getFont().deriveFont(15f));
            p5.add(varlık);
            JButton varlıklar = new JButton("Tüm Hesap Varlıkları");
            Arayuz.mybounds(varlıklar,250,40,kx-300,ky-100);
            p5.add(varlıklar);
            JButton hareketler = new JButton("Tüm Hesap Hareketleri");
            Arayuz.mybounds(hareketler,250,40,kx+300,ky-100);
            p5.add(hareketler);
            DefaultTableModel dtmp5 = new DefaultTableModel(0,0);
            String headerp5[] = new String[] {"Hesap","Açıklama","Değişim"};
            dtmp5.setColumnIdentifiers(headerp5);
            JTable ayrıntıhareket = new JTable();
            ayrıntıhareket.setRowSelectionAllowed(true);
            ayrıntıhareket.setDefaultEditor(Object.class, null);
            ayrıntıhareket.setModel(dtmp5);
            JScrollPane ayrıntısp = new JScrollPane(ayrıntıhareket);
            Arayuz.mybounds(ayrıntısp,kx-150,ky-200,kx,80);
            p5.add(ayrıntısp);
            hesaptxtp5.setBorder(hesaptxt.getBorder());
            JScrollPane hesapsp = new JScrollPane(hesaptxtp5);
            Arayuz.mybounds(hesapsp,kx-150,ky-200,kx,80);
            p5.add(hesapsp);
            
        
        //panel 6
            
            JButton limitbasvuru = new JButton();
            limitbasvuru.setBounds(40,150,150,50);
            limitbasvuru.setLayout(new BorderLayout());
            limitbasvuru.add(BorderLayout.NORTH,new JLabel("Kredi Kartı"));
            limitbasvuru.add(BorderLayout.SOUTH,new JLabel("Limit Başvurusu"));
            p6.add(limitbasvuru);
            JTextField limitbasvurucvp = new JTextField();
            limitbasvurucvp.setFont(limitbasvurucvp.getFont().deriveFont(17f));
            limitbasvurucvp.setBounds(200,150,120,50);
            p6.add(limitbasvurucvp);
            JButton limitbasvurugonder = new JButton("Gönder");
            limitbasvurugonder.setBounds(330,150,100,50);
            p6.add(limitbasvurugonder);
            JButton borcode = new JButton("Borç Öde");
            borcode.setBounds(40,220,150,50);
            p6.add(borcode);
            JButton ayar = new JButton("Kart Ayarları");
            ayar.setBounds(40,290,150,50);
            p6.add(ayar);
            JButton hareketp6 = new JButton("Kart Hareketleri");
            hareketp6.setBounds(40,360,150,50);
            p6.add(hareketp6);
            JLabel secimp6 = new JLabel("İşlem Yapılacak Hesap Seçimi");
            secimp6.setFont(secimp6.getFont().deriveFont(16f));
            Arayuz.mybounds(secimp6,230,30,kx+500,30);
            p6.add(secimp6);
            
            
        //panel 7
            
//            JLabel messagelabel = new JLabel("Destek ve Yardım Hattı");
//            Arayuz.mybounds(messagelabel, 200, 30, kx, 50);
//            messagelabel.setFont(messagelabel.getFont().deriveFont(15f));
//            messagelabel.setHorizontalTextPosition(JLabel.CENTER);
//            p7.add(messagelabel);
//            Vector<JTextArea> messageclient = new Vector();
//            Vector<JTextArea> messagesupport = new Vector();
//            JTextArea chat = new JTextArea();
//            chat.setBounds(50,ky-90,kx-200,70);
//            chat.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
//            JButton send = new JButton("Gönder");
//            send.setBounds(kx-145,ky-90,100,50);
//            SpringLayout spring = new SpringLayout();
//            ScrollablePanel mainpanel = new ScrollablePanel();
//            mainpanel.setLayout(new BoxLayout(mainpanel,BoxLayout.X_AXIS));
//            ScrollablePanel messagepanel2 = new ScrollablePanel();
//            messagepanel2.setLayout(new BoxLayout(messagepanel2,BoxLayout.PAGE_AXIS));
//            ScrollablePanel messagepanel = new ScrollablePanel();
//            messagepanel.setLayout(new BoxLayout(messagepanel,BoxLayout.PAGE_AXIS));
//            mainpanel.add(messagepanel);
//            mainpanel.add(messagepanel2);
//            JScrollPane messagesp = new JScrollPane(mainpanel);
//            p7.add(chat);
//            p7.add(send);
//            Arayuz.mybounds(messagesp,kx-100,ky-200,kx,100);
//            p7.add(messagesp);
//            JButton messagerefresh = new JButton("Yenile");
//            messagerefresh.setBounds(50,50,80,50);
//            p7.add(messagerefresh);
//            messagesp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//            
//            send.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                
//                String message = new String(chat.getText());
//                messageclient.add(new JTextArea(message));
//                messageclient.add(new JTextArea(message));
//                messageclient.lastElement().setLineWrap(true);
//                messageclient.lastElement().setWrapStyleWord(true);
//                messageclient.lastElement().setBorder(BorderFactory.createLineBorder(Color.blue,2));
//                messageclient.elementAt(messageclient.size()-2).setBorder(BorderFactory.createLineBorder(Color.red,2));
//                messageclient.elementAt(messageclient.size()-2).setLineWrap(true);
//                messageclient.elementAt(messageclient.size()-2).setWrapStyleWord(true);
//                messagepanel.add(messageclient.lastElement());
//                messagepanel2.add(messageclient.elementAt(messageclient.size()-2));
//                messagesp.setVisible(false);
//                messagesp.setVisible(true);
//            }});
        
        //panel 8
        
            JButton hesapbuton = new JButton("Parola Değiştir");
            Arayuz.mybounds(hesapbuton,150,30,kx,300);
            p8.add(hesapbuton);
            JPasswordField hesapyenisifre = new JPasswordField();
            Arayuz.mybounds(hesapyenisifre,150,30,kx,150);
            p8.add(hesapyenisifre);
            JPasswordField hesaponaysifre = new JPasswordField();
            Arayuz.mybounds(hesaponaysifre,150,30,kx,200);
            p8.add(hesaponaysifre);
            JPasswordField hesapeskisifre = new JPasswordField();
            Arayuz.mybounds(hesapeskisifre,150,30,kx,250);
            p8.add(hesapeskisifre);
            JLabel hesaplabel1 = new JLabel("Yeni Parola: ");
            Arayuz.mybounds(hesaplabel1,150 , 30,kx-200, 150);
            p8.add(hesaplabel1);
            JLabel hesaplabel2 = new JLabel("Yeni Parola Onay: ");
            Arayuz.mybounds(hesaplabel2,150,30 ,kx-200, 200);
            p8.add(hesaplabel2);
            JLabel hesaplabel3 = new JLabel("Eski Parola");
            Arayuz.mybounds(hesaplabel3,150,30, kx-200, 250);
            p8.add(hesaplabel3);
            
        //panel exit
            
            oturumkapat.setFont(oturumkapat.getFont().deriveFont(15f));
            oturumkapat.setBackground(Color.red);
            oturumkapat.setForeground(Color.white);
            Arayuz.mybounds(oturumkapat,150,40,kx,ky/2-50);
            exit.add(oturumkapat);
            JLabel izin = new JLabel("Oturumu kapatmak istediğinizden emin misiniz?");
            izin.setFont(izin.getFont().deriveFont(15f));
            Arayuz.mybounds(izin,350,30,kx,ky/2-100);
            exit.add(izin);
        
        //listener field
        tp.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent gecis){
                if(tp.getSelectedIndex()==0){
                    dosyaOku(iterator);
                    p1.add(hesaptxt);
                    p1.add(hesaplist);
                    p1.add(hareketsp);
                    Arayuz.mybounds(hesaptxt,kx/2,ky/2,kx+300,100);
                    Arayuz.mybounds(hesaplist,kx/3,ky/2,kx-500,100);
                    Arayuz.mybounds(hareketsp,kx/2,ky/2,kx+300,100);
                    hesaptxt.setVisible(true);
                    hareketsp.setVisible(false);
                    hesaplist.setVisible(true);
                }
                if(tp.getSelectedIndex()==1){
                    dosyaOku(iterator);
                    p2.add(hesaplist);
                    p2.add(hesaptxt);
                    Arayuz.mybounds(hesaplist,kx/3,ky/2,kx-500,100);
                    Arayuz.mybounds(hesaptxt,kx/3,ky/5,kx-500,ky-200);
                    Arayuz.mybounds(hesaptxtp2,kx/3,ky/5,kx+500,ky-200);
                    hesaptxtp2.setVisible(false);
                    hesaptxt.setVisible(false);
                    hesaplistcopy.setVisible(false);
                    hesaplist.setVisible(false);
                    transfer.setVisible(false);
                    p2label.setVisible(false);
                    tto.setVisible(false);
                    okisareti.setVisible(false);
                    ltto.setVisible(false);
                    totto.setVisible(false);
                    ltme.setVisible(false);
                }
                if(tp.getSelectedIndex()==2){
                    dosyaOku(iterator);
                    p3.add(hesaplist);
                    p3.add(hesaptxt);
                    Arayuz.mybounds(hesaptxt,kx/4,ky/5,(2*kx)-(kx/3),300);
                    Arayuz.mybounds(hesaplist,kx/3,ky/2,kx+50,175);
                    hesaplist.setVisible(true);
                    hesaptxt.setVisible(true);
                }
                if(tp.getSelectedIndex()==3){
                    dosyaOku(iterator);
                    p4.add(hesaplist);
                    p4.add(hesaptxt);
                    Arayuz.mybounds(hesaplist,kx/3,ky/2,kx+500,100);
                    Arayuz.mybounds(hesaptxt,kx/3,ky/5,kx+500,ky-200);
                    hesaplist.setVisible(true);
                    hesaptxt.setVisible(true);
                }
                if(tp.getSelectedIndex()==4){
                    dosyaOku(iterator);
                    hesapsp.setVisible(true);
                    ayrıntısp.setVisible(false);
                    hesaptxtp5update();
                    tablefunc(dtmp5,5);
                }
                if(tp.getSelectedIndex()==5){
                   dosyaOku(iterator);
                   p6.add(hesaplist);
                   p6.add(hesaptxt);
                   Arayuz.mybounds(hesaplist,kx/3,ky/2,kx+500,100);
                   Arayuz.mybounds(hesaptxt,kx/3,ky/5,kx+500,ky-200);
                   hesaptxt.setVisible(true);
                   hesaplist.setVisible(true);
                   limitbasvuru.setVisible(false);
                   limitbasvurucvp.setVisible(false);
                   hareketp6.setVisible(false);
                   borcode.setVisible(false);
                   ayar.setVisible(false);
                   limitbasvurugonder.setVisible(false);
                   hesaplist.clearSelection();
                }
                if(tp.getSelectedIndex()==6){
                    dosyaOku(iterator);
                    
                }
                if(tp.getSelectedIndex()==7){
                    dosyaOku(iterator);
                    
                }
            }
        });
        
        ayar.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    
                }
            });
        
        hareketp6.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    tp.setSelectedIndex(0);
                    hesaptxt.setVisible(false);
                    hareketsp.setVisible(true);
                }
            });
        
        borcode.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    tp.setSelectedIndex(1);
                    mytransfer.doClick();
                    hesaplistcopy.setSelectedIndex(hesaplist.getSelectedIndex());
                    hesaplist.clearSelection();
                }
            });
        
        limitbasvuru.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    limitbasvurucvp.setVisible(true);
                    limitbasvurugonder.setVisible(true);
                }
            });
        
        limitbasvurugonder.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(!limitbasvurucvp.getText().equals("")){
                    if(limitbasvurucvp.getText().contains(".")){
                        int precision = limitbasvurucvp.getText().length()-(limitbasvurucvp.getText().indexOf(".")+1);
                        if(precision>2){
                            limitbasvurucvp.setText(limitbasvurucvp.getText().substring(0,limitbasvurucvp.getText().indexOf(".")+3));
                        }else{
                            for(int x =2;x>precision;x--){
                                limitbasvurucvp.setText(limitbasvurucvp.getText()+"0");
                            }
                        }
                    }else{
                        limitbasvurucvp.setText(limitbasvurucvp.getText() + ".00");
                    }
                    try{
                    File basvuru = new File("Approval00000.txt");
                    OutputStreamWriter basvurufw = new OutputStreamWriter(new FileOutputStream(basvuru,true),StandardCharsets.UTF_8);
                    BufferedWriter basvurubw = new BufferedWriter(basvurufw);
                    basvurubw.write(kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(1) + "\n" 
                    + ad+" "+soyad+","+"Kredi Kartı Limit,"+"Limit Yenileme"+","+limitbasvurucvp.getText()+"\n");
                    basvurubw.close();
                    }catch (IOException ex){
                        System.out.print(ex.getMessage());
                    }
                    JOptionPane.showMessageDialog(new JFrame(),"İşlem Başarılı","BAŞARILI",JOptionPane.INFORMATION_MESSAGE);
                    limitbasvurugonder.setVisible(false);
                    limitbasvurucvp.setText("");
                    limitbasvurucvp.setVisible(false);
                }else{JOptionPane.showMessageDialog(new JFrame(),"Bilgileri Eksik Veya Yanlış Girdiniz","UYARI",JOptionPane.WARNING_MESSAGE);}
                }
            });
        
        limitbasvurucvp.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                boolean check = kredimiktarcvp.getText().contains(".");
                char c = e.getKeyChar();
                if((c<'0' || c>'9')&&(c != KeyEvent.VK_BACK_SPACE)&&((c!='.')||check)){
                    e.consume();
                }
            }
        });
        
        hesapbuton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String yeni = new String(hesapyenisifre.getPassword());
                String onay = new String(hesaponaysifre.getPassword());
                String eski = new String(hesapeskisifre.getPassword());
                if(yeni.equals(onay)&& !yeni.isEmpty() && !eski.isEmpty()){
                    String eskisifre = null;
                    try{
                        File signed = new File("Signed00000.txt");
                        InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signed),StandardCharsets.UTF_8);
                        BufferedReader signedbr = new BufferedReader(signedfr);
                        for(int x = 0;x<(iterator*6);x++){
                            signedbr.readLine();
                        }
                        signedbr.readLine();
                        signedbr.readLine();
                        signedbr.readLine();
                        signedbr.readLine();
                        eskisifre = signedbr.readLine();
                        signedbr.close();
                        }catch(IOException ex){
                            System.out.print(ex.getMessage());
                    }
                    if(eski.equals(eskisifre)){
                        try{
                        File signed = new File("Signed00000.txt");
                        InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signed),StandardCharsets.UTF_8);
                        BufferedReader signedbr = new BufferedReader(signedfr);
                        String line = new String();
                        String lines = new String();
                        for(int x = 0;x<(iterator*6);x++){
                            lines = lines + "\n"+signedbr.readLine();
                        }
                        lines = lines + "\n" +signedbr.readLine();
                        lines = lines + "\n" +signedbr.readLine();
                        lines = lines + "\n" +signedbr.readLine();
                        lines = lines + "\n" +signedbr.readLine();
                        signedbr.readLine();
                        lines = lines + "\n"+yeni;
                        while((line = signedbr.readLine())!=null){
                            lines = lines + "\n"+line;
                        }
                        lines = lines.trim();
                        lines = lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                        signedbr.close();
                        OutputStreamWriter signedfw = new OutputStreamWriter(new FileOutputStream(signed),StandardCharsets.UTF_8);
                        BufferedWriter signedbw = new BufferedWriter(signedfw);
                        signedbw.write(lines);
                        signedbw.close();
                        }catch(IOException ex){
                            System.out.print(ex.getMessage());
                    }
                        JOptionPane.showMessageDialog(new JFrame(),"İşlem Başarılı","BAŞARILI",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(new JFrame(),"Bilgileri Eksik Veya Yanlış Girdiniz","UYARI",JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"Bilgileri Eksik Veya Yanlış Girdiniz","UYARI",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        kredibuton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(!hesaplist.isSelectionEmpty()&&!kredimiktarcvp.getText().equals("")&&kredinedencvp.getSelectedIndex()!=0){
                        if(kredimiktarcvp.getText().contains(".")){
                            int precision = kredimiktarcvp.getText().length()-(kredimiktarcvp.getText().indexOf(".")+1);
                            if(precision>2){
                                kredimiktarcvp.setText(kredimiktarcvp.getText().substring(0,kredimiktarcvp.getText().indexOf(".")+3));
                            }else{
                                for(int x =2;x>precision;x--){
                                    kredimiktarcvp.setText(kredimiktarcvp.getText()+"0");
                                }
                            }
                        }else{
                            kredimiktarcvp.setText(kredimiktarcvp.getText() + ".00");
                        }
                        try{
                        File basvuru = new File("Approval00000.txt");
                        OutputStreamWriter basvurufw = new OutputStreamWriter(new FileOutputStream(basvuru,true),StandardCharsets.UTF_8);
                        BufferedWriter basvurubw = new BufferedWriter(basvurufw);
                        basvurubw.write(kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(1) + "\n" 
                        + ad+" "+soyad+","+"Kredi,"+kredinedencvp.getSelectedItem()+","+kredimiktarcvp.getText()+"\n");
                        basvurubw.close();
                        }catch (IOException ex){
                            System.out.print(ex.getMessage());
                        }
                        JOptionPane.showMessageDialog(new JFrame(),"İşlem Başarılı","BAŞARILI",JOptionPane.INFORMATION_MESSAGE);
                    }else{JOptionPane.showMessageDialog(new JFrame(),"Bilgileri Eksik Veya Yanlış Girdiniz","UYARI",JOptionPane.WARNING_MESSAGE);}
                }
            });
        
        kredimiktarcvp.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                boolean check = kredimiktarcvp.getText().contains(".");
                char c = e.getKeyChar();
                if((c<'0' || c>'9')&&(c != KeyEvent.VK_BACK_SPACE)&&((c!='.')||check)){
                    e.consume();
                }
            }
        });
        
        hareket.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    hesaptxt.setVisible(false);
                    hareketsp.setVisible(true);
                }
            });
        
        hesap.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                hareketsp.setVisible(false);
                hesaptxt.setVisible(true);
            }
            });
        
        ourtransfer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                hesaptxtp2.setVisible(false);
                hesaptxt.setVisible(true);
                hesaplistcopy.setVisible(false);
                hesaplist.setVisible(true);
                transfer.setVisible(true);
                p2label.setVisible(true);
                tto.setVisible(true);
                okisareti.setVisible(true);
                ltto.setVisible(true);
                totto.setVisible(true);
                ltme.setVisible(true);
                transfertype = false;
                }});

        mytransfer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                hesaptxtp2.setVisible(true);
                hesaptxt.setVisible(true);
                hesaplistcopy.setVisible(true);
                hesaplist.setVisible(true);
                transfer.setVisible(true);
                p2label.setVisible(true);
                tto.setVisible(true);
                okisareti.setVisible(true);
                ltto.setVisible(false);
                totto.setVisible(false);
                ltme.setVisible(true);
                transfertype = true;
                }});
        
        varlıklar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                hesapsp.setVisible(true);
                ayrıntısp.setVisible(false);
                hesaptxtp5update();
                }});
        
        hareketler.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ayrıntısp.setVisible(true);
                hesapsp.setVisible(false);
                tablefunc(dtmp5,5);
                }});
        
        hesaplist.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                if(!e.getValueIsAdjusting()&&hesaplist.getSelectedIndex()!=-1){
                    if(kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(3).equals("null")){
                        hesaptxt.setText(ad+" "+soyad+ "\nKart No: "+
                        kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(1)+
                        "\nBakiye: "+kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(2)
                        + " TL");
                    }else{
                        hesaptxt.setText(ad+" "+soyad+ "\nKart No: "+
                        kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(1)+
                        "\nToplam Limit: "+ kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(3)
                        +" TL\nKullanılabilir: "+kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(2)
                        +" TL");
                    }
                    tablefunc(dtmp1,1);
                    if(kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(0).equals("K")){
                        limitbasvuru.setVisible(true);
                        limitbasvurucvp.setVisible(false);
                        hareketp6.setVisible(true);
                        borcode.setVisible(true);
                        ayar.setVisible(true);
                    }else if(kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(0).equals("B")){
                        limitbasvuru.setVisible(false);
                        limitbasvurucvp.setVisible(false);
                        borcode.setVisible(false);
                        ayar.setVisible(true);
                        hareketp6.setVisible(true);
                    }
                }
                }});
        
        hesaplistcopy.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                if(!e.getValueIsAdjusting()&&hesaplistcopy.getSelectedIndex()!=-1){
                    if(kartlar.elementAt(hesaplistcopy.getSelectedIndex()).elementAt(3).equals("null")){
                        hesaptxtp2.setText(ad+" "+soyad+ "\nKart No: "+
                        kartlar.elementAt(hesaplistcopy.getSelectedIndex()).elementAt(1)+
                        "\nBakiye: "+kartlar.elementAt(hesaplistcopy.getSelectedIndex()).elementAt(2)
                        + " TL");
                    }else{
                        hesaptxtp2.setText(ad+" "+soyad+ "\nKart No: "+
                        kartlar.elementAt(hesaplistcopy.getSelectedIndex()).elementAt(1)+
                        "\nToplam Limit: "+ kartlar.elementAt(hesaplistcopy.getSelectedIndex()).elementAt(3)
                        +" TL\nKullanılabilir: "+kartlar.elementAt(hesaplistcopy.getSelectedIndex()).elementAt(2)
                        +" TL");
                    }
                    }
                
                }});
        
        odemecombo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tablefunc(dtmp3,3);
            }});
        
        odeme.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int select = odemetable.getSelectedRow();
                if(!hesaplist.isSelectionEmpty()&&odemetable.getSelectedRow()!=-1 && Double.parseDouble(kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(2))>=Double.parseDouble(dtmp3.getValueAt(select, 2).toString())){
                    for(int x =0;x<faturalar.size();x++){
                        if(faturalar.elementAt(x).elementAt(2).equals(dtmp3.getValueAt(select, 0))){
                            faturalar.removeElementAt(x);
                            try{
                                File odemefile = new File("Bills00000.txt");
                                InputStreamReader odemefr = new InputStreamReader(new FileInputStream(odemefile),StandardCharsets.UTF_8);
                                BufferedReader odemebr = new BufferedReader(odemefr);
                                String line = new String();
                                String lines = new String();
                                while((line = odemebr.readLine())!=null){
                                    lines = lines +"\n"+line;
                                    if(line.equals(hesapid)){break;}
                                }
                                line = odemebr.readLine();
                                lines = lines + "\n";
                                if(!faturalar.isEmpty()){
                                    for(Vector<String> fatura: faturalar){
                                        lines = lines + fatura.elementAt(0)+",";
                                        lines = lines + fatura.elementAt(1)+",";
                                        lines = lines + fatura.elementAt(2)+";";
                                    }
                                }else{lines = lines + ";";}
                                while((line = odemebr.readLine())!=null){
                                    lines = lines + "\n"+line;
                                }
                                lines = lines.trim();
                                lines = lines.replaceAll("(?m)^[ \t]*\r?\n", "");
                                odemebr.close();
                                OutputStreamWriter odemefw = new OutputStreamWriter(new FileOutputStream(odemefile),StandardCharsets.UTF_8);
                                BufferedWriter odemebw = new BufferedWriter(odemefw);
                                odemebw.write(lines);
                                odemebw.close();
                                pay(dtmp3.getValueAt(select, 1).toString(),dtmp3.getValueAt(select, 2).toString());
                                dtmp3.removeRow(select);
                                dosyaOku(iterator);
                            }catch(IOException ex){
                                System.out.print(ex.getMessage());
                            }
                            hesaplist.clearSelection();
                            hesaplistcopy.clearSelection();
                            JOptionPane.showMessageDialog(new JFrame(),"İşlem Başarılı","BAŞARILI",JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"Bilgileri Eksik Veya Yanlış Girdiniz","UYARI",JOptionPane.WARNING_MESSAGE);
                }
            }});
        
        transfer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(tto.getText().contains(".")){
                    int precision = tto.getText().length()-(tto.getText().indexOf(".")+1);
                    if(precision>2){
                        tto.setText(tto.getText().substring(0,tto.getText().indexOf(".")+3));
                    }else{
                        for(int x =2;x>precision;x--){
                            tto.setText(tto.getText()+"0");
                        }
                    }
                }else{
                    tto.setText(tto.getText() + ".00");
                }
                
                if(transfertype){
                    if(!tto.getText().equals("")&&!hesaplist.isSelectionEmpty()&&!hesaplistcopy.isSelectionEmpty()&&Double.parseDouble(tto.getText())>0&&(hesaplist.getSelectedIndex()!=hesaplistcopy.getSelectedIndex())){
                        if(Double.parseDouble(kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(2))>Double.parseDouble(tto.getText())){
                            paratransfer(transfertype,null,tto.getText());
                            dosyaOku(iterator);
                            tto.setText("");
                            hesaplist.clearSelection();
                            hesaplistcopy.clearSelection();
                            JOptionPane.showMessageDialog(new JFrame(),"İşlem Başarılı","BAŞARILI",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(new JFrame(),"Yetersiz Bakiye","UYARI",JOptionPane.WARNING_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(new JFrame(),"Bilgileri Eksik Veya Yanlış Girdiniz","UYARI",JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    if(!totto.getText().equals("")&&!tto.getText().equals("")&&!hesaplist.isSelectionEmpty()&&Double.parseDouble(tto.getText())>0){
                        if(Double.parseDouble(kartlar.elementAt(hesaplist.getSelectedIndex()).elementAt(2))>Double.parseDouble(tto.getText())){
                            paratransfer(transfertype,totto.getText(),tto.getText());
                            dosyaOku(iterator);
                            tto.setText("");
                            hesaplist.clearSelection();
                            hesaplistcopy.clearSelection();
                            JOptionPane.showMessageDialog(new JFrame(),"İşlem Başarılı","BAŞARILI",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(new JFrame(),"Yetersiz Bakiye","UYARI",JOptionPane.WARNING_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(new JFrame(),"Bilgileri Eksik Veya Yanlış Girdiniz","UYARI",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }});
        
        tto.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                boolean check = tto.getText().contains(".");
                char c = e.getKeyChar();
                if((c<'0' || c>'9')&&(c != KeyEvent.VK_BACK_SPACE)&&((c!='.')||check)){
                    e.consume();
                }
            }
        });
        
        oturumkapat.addActionListener(this);
        
    }
    
    public void giriliyor(){
        
        kframe.add(tp);
        kframe.setResizable(false);
        kframe.setSize(1000,700);
        frame.setVisible(false);
        kframe.setLocation(200,200);
        kframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        tp.addTab("Hesap",null,p1,"Hesap bilgileri ve basit işlemler");
        tp.addTab("Para Transferi",null,p2,"Para transfer işlemleri");
        tp.addTab("Ödemeler",null,p3,"Fatura ödeme işlemleri");
        tp.addTab("Krediler",null,p4,"Kredi başvuru işlemleri");
        tp.addTab("Varlıklar",null,p5,"Detaylı hesap varlıkları");
        tp.addTab("Hesap işlemleri",null,p6,"Varlıklarınız ve hesaplarınız üzerinden tüm işlemler");
        //tp.addTab("Destek",null,p7,"Yardım ve Destek Hattı");
        tp.addTab("Hesap Ayarları",null,p8,"Hesap bilgilerinizi değiştirin");
        tp.addTab("Oturumu Kapat",null,exit,null);
        
        tp.setBounds(0,0,1000,700);
        tp.setBackground(Color.lightGray);
        tp.setForeground(Color.white);
        tp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tp.setOpaque(true);
        
        kframe.setVisible(true);
        kframe.setLayout(null);
        
        {
            p1.setBorder(BorderFactory.createLineBorder(Color.lightGray, 10));
            p1.setBackground(Color.WHITE);
            p1.setLayout(null);
            p2.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p2.setBackground(Color.WHITE);
            p2.setLayout(null);
            p3.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p3.setBackground(Color.WHITE);
            p3.setLayout(null);
            p4.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p4.setBackground(Color.WHITE);
            p4.setLayout(null);
            p5.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p5.setBackground(Color.WHITE);
            p5.setLayout(null);
            p6.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p6.setBackground(Color.WHITE);
            p6.setLayout(null);
            p7.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p7.setBackground(Color.WHITE);
            p7.setLayout(null);
            p8.setBorder(BorderFactory.createLineBorder(Color.lightGray,10));
            p8.setBackground(Color.WHITE);
            p8.setLayout(null);
            exit.setBackground(Color.WHITE);
            exit.setLayout(null);
            for(int x=0;x<7;x++){
                tp.setBackgroundAt(x, Color.blue);
                tp.setMnemonicAt(x, KeyEvent.VK_0);
            }
            tp.setBackgroundAt(7, Color.red);
        }
    }
}

class Arayuz{
    
    Arayuz(){
        
        int x=500,y=500;
        
        mybounds(TemelGUI.girislabel1,100,30,300,100);
        mybounds(TemelGUI.kayitlabel1,100,30,300,100);
        mybounds(TemelGUI.girislabel2,100,30,300,150);
        mybounds(TemelGUI.kayitlabel2,100,30,300,150);
        mybounds(TemelGUI.kayitlabel3,100,30,300,200);
        mybounds(TemelGUI.kayitlabel4,100,30,300,250);
        mybounds(TemelGUI.girislabel3,200,40,x,50);
        TemelGUI.girislabel3.setFont(TemelGUI.girislabel3.getFont().deriveFont(20f));
        TemelGUI.girislabel3.setHorizontalAlignment(JLabel.CENTER);
        TemelGUI.giristxt.setEditable(false);
        TemelGUI.giristxt.setFont(TemelGUI.giristxt.getFont().deriveFont(13f));
        TemelGUI.giristxt.setFont(TemelGUI.giristxt.getFont().deriveFont(Font.BOLD));
        TemelGUI.giristxt.setForeground(Color.red);
        TemelGUI.giristxt.setLineWrap(true);
        TemelGUI.giristxt.setWrapStyleWord(true);
        TemelGUI.giristxt.setText(TemelGUI.girisstring1);
        mybounds(TemelGUI.girisno,100,30,x,100);
        mybounds(TemelGUI.kayitad,100,30,x,100);
        mybounds(TemelGUI.girissifre,100,30,x,150);
        mybounds(TemelGUI.kayitsoyad,100,30,x,150);
        mybounds(TemelGUI.kayitsifre,100,30,x,200);
        mybounds(TemelGUI.kayitsifreonay,100,30,x,250);
        mybounds(TemelGUI.kayitbuton,100,30,x,300);
        mybounds(TemelGUI.girisgecis,150,30,x,350);
        mybounds(TemelGUI.girisbuton,100,30,x,200);
        mybounds(TemelGUI.kayitgecis,150,30,x,250);
        mybounds(TemelGUI.testbilgi,150,30,x-160,300);
        mybounds(TemelGUI.info,150,30,x+160,300);
        mybounds(TemelGUI.girissp,450,110,x,335);
        TemelGUI.frame.getContentPane().setBackground(Color.white);
        TemelGUI.frame.setSize(x,y);
        TemelGUI.frame.setLocation(200,200);
        TemelGUI.frame.setResizable(false);
        TemelGUI.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TemelGUI.frame.add(TemelGUI.girissp);
        TemelGUI.frame.add(TemelGUI.info);
        TemelGUI.frame.add(TemelGUI.testbilgi);
        TemelGUI.frame.add(TemelGUI.girislabel1);
        TemelGUI.frame.add(TemelGUI.girislabel2);
        TemelGUI.frame.add(TemelGUI.girislabel3);
        TemelGUI.frame.add(TemelGUI.girisbuton);
        TemelGUI.frame.add(TemelGUI.girissifre);
        TemelGUI.frame.add(TemelGUI.girisno);
        TemelGUI.frame.add(TemelGUI.girisgecis);
        TemelGUI.frame.add(TemelGUI.kayitbuton);
        TemelGUI.frame.add(TemelGUI.kayitgecis);
        TemelGUI.frame.add(TemelGUI.kayitlabel1);
        TemelGUI.frame.add(TemelGUI.kayitlabel2);
        TemelGUI.frame.add(TemelGUI.kayitlabel3);
        TemelGUI.frame.add(TemelGUI.kayitlabel4);
        TemelGUI.frame.add(TemelGUI.kayitad);
        TemelGUI.frame.add(TemelGUI.kayitsoyad);
        TemelGUI.frame.add(TemelGUI.kayitsifre);
        TemelGUI.frame.add(TemelGUI.kayitsifreonay);
        TemelGUI.kayitbuton.setVisible(false);
        TemelGUI.girisgecis.setVisible(false);
        TemelGUI.kayitlabel1.setVisible(false);
        TemelGUI.kayitlabel2.setVisible(false);
        TemelGUI.kayitlabel3.setVisible(false);
        TemelGUI.kayitlabel4.setVisible(false);
        TemelGUI.kayitad.setVisible(false);
        TemelGUI.kayitsoyad.setVisible(false);
        TemelGUI.kayitsifre.setVisible(false);
        TemelGUI.kayitsifreonay.setVisible(false);
        TemelGUI.frame.setLayout(null);
        TemelGUI.frame.setVisible(true);
        TemelGUI.testbilgi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                TemelGUI.giristxt.setText(TemelGUI.girisstring1);
            }
        });
        TemelGUI.info.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                TemelGUI.giristxt.setText(TemelGUI.girisstring2);
            }
        });
        TemelGUI.girisgecis.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                TemelGUI.kayitbuton.setVisible(false);
                TemelGUI.girisgecis.setVisible(false);
                TemelGUI.kayitlabel1.setVisible(false);
                TemelGUI.kayitlabel2.setVisible(false);
                TemelGUI.kayitlabel3.setVisible(false);
                TemelGUI.kayitlabel4.setVisible(false);
                TemelGUI.kayitad.setVisible(false);
                TemelGUI.kayitsoyad.setVisible(false);
                TemelGUI.kayitsifre.setVisible(false);
                TemelGUI.kayitsifreonay.setVisible(false);
                TemelGUI.girislabel1.setVisible(true);
                TemelGUI.girislabel2.setVisible(true);
                TemelGUI.girislabel3.setVisible(true);
                TemelGUI.girisbuton.setVisible(true);
                TemelGUI.girisno.setVisible(true);
                TemelGUI.girissifre.setVisible(true);
                TemelGUI.kayitgecis.setVisible(true);
                TemelGUI.testbilgi.setVisible(true);
                TemelGUI.info.setVisible(true);
                TemelGUI.girissp.setVisible(true);
            }
        });
        TemelGUI.kayitgecis.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                TemelGUI.kayitbuton.setVisible(true);
                TemelGUI.kayitgecis.setVisible(false);
                TemelGUI.girisgecis.setVisible(true);
                TemelGUI.kayitlabel1.setVisible(true);
                TemelGUI.kayitlabel2.setVisible(true);
                TemelGUI.kayitlabel3.setVisible(true);
                TemelGUI.kayitlabel4.setVisible(true);
                TemelGUI.kayitad.setVisible(true);
                TemelGUI.kayitsoyad.setVisible(true);
                TemelGUI.kayitsifre.setVisible(true);
                TemelGUI.kayitsifreonay.setVisible(true);
                TemelGUI.girislabel1.setVisible(false);
                TemelGUI.girislabel2.setVisible(false);
                TemelGUI.girislabel3.setVisible(true);
                TemelGUI.girisbuton.setVisible(false);
                TemelGUI.girisno.setVisible(false);
                TemelGUI.girissifre.setVisible(false);
                TemelGUI.testbilgi.setVisible(false);
                TemelGUI.info.setVisible(false);
                TemelGUI.girissp.setVisible(false);
            }
        });
        TemelGUI.kayitbuton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if((!TemelGUI.kayitad.getText().equals(""))&&(!TemelGUI.kayitsoyad.getText().equals(""))&&(!new String(TemelGUI.kayitsifre.getPassword()).equals(""))&&(!new String(TemelGUI.kayitsifreonay.getPassword()).equals(""))&&(new String(TemelGUI.kayitsifre.getPassword()).equals(new String(TemelGUI.kayitsifreonay.getPassword())))){
                    String ad = new String(TemelGUI.kayitad.getText());
                    String soyad = new String(TemelGUI.kayitsoyad.getText());
                    String sifre = new String(TemelGUI.kayitsifre.getPassword());
                    ad = ad.trim();
                    ad = ad.replaceAll("(?m)^[ \t]*\r?\n", "");
                    ad = ad.substring(0, 1).toUpperCase()+ad.substring(1).toLowerCase();
                    soyad = soyad.trim();
                    soyad = soyad.replaceAll("(?m)^[ \t]*\r?\n", "");
                    soyad = soyad.toUpperCase();
                    sifre = sifre.trim();
                    sifre = sifre.replaceAll("(?m)^[ \t]*\r?\n", "");
                    try{
                        File sign = new File("SignApproval00000.txt");
                        OutputStreamWriter signfr = new OutputStreamWriter(new FileOutputStream(sign,true),StandardCharsets.UTF_8);
                        BufferedWriter signbr = new BufferedWriter(signfr);
                        signbr.write(ad+","+soyad+","+sifre+","+"UH"+"\n");
                        signbr.close();
                    }catch(IOException ex) {
                        System.out.print(ex.getMessage());
                    }
                    JOptionPane.showMessageDialog(new JFrame(),"İşlem Başarılı","BAŞARILI",JOptionPane.INFORMATION_MESSAGE);
                    TemelGUI.kayitsifre.setText("");
                    TemelGUI.kayitsifreonay.setText("");
                    TemelGUI.kayitad.setText("");
                    TemelGUI.kayitsoyad.setText("");
                    TemelGUI.girisgecis.doClick();
                }else{
                    TemelGUI.kayitsifre.setText("");
                    TemelGUI.kayitsifreonay.setText("");
                    JOptionPane.showMessageDialog(new JFrame(),"Bilgileri Eksik Veya Yanlış Girdiniz","UYARI",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        TemelGUI.girisbuton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ACT){
            int iterator = 0;
            boolean perso=false,adm=false,kul=false;
            String no = new String(TemelGUI.girisno.getText());
            String pass = new String (TemelGUI.girissifre.getPassword());
            try{
                File signed = new File("Signed00000.txt");
                InputStreamReader signedfr = new InputStreamReader(new FileInputStream(signed),StandardCharsets.UTF_8);
                BufferedReader signedbr = new BufferedReader(signedfr);
                String tempno = new String();
                String temppass = new String();
                for(int it=0;;it++){
                    if((tempno = signedbr.readLine())!=null){
                        if(tempno.equals(no)){
                            signedbr.readLine();
                            signedbr.readLine();
                            signedbr.readLine();
                            temppass = signedbr.readLine();
                            if(temppass.equals(pass)){
                                kul = true;
                                iterator = it;
                                break;
                            }
                            signedbr.readLine();
                        }else{
                            signedbr.readLine();
                            signedbr.readLine();
                            signedbr.readLine();
                            signedbr.readLine();
                            signedbr.readLine();
                        }
                    }else{break;}
                }
                signedbr.close();
                
                if(!kul){
                    File admin = new File("Admin000.txt");
                    InputStreamReader adminfr = new InputStreamReader(new FileInputStream(admin),StandardCharsets.UTF_8);
                    BufferedReader adminbr = new BufferedReader(adminfr);
                    String y_or_p = new String();
                    for(int it=0;;it++){
                        if((tempno = adminbr.readLine())!=null){
                            if(tempno.equals(no)){
                                adminbr.readLine();
                                adminbr.readLine();
                                y_or_p = adminbr.readLine().split(",")[0];
                                if(y_or_p.equals("Y")){
                                    temppass = adminbr.readLine();
                                    if(temppass.equals(pass)){
                                        adm = true;
                                        iterator = it;
                                        break;
                                    }else{break;}
                                }else if(y_or_p.equals("P")){
                                    temppass = adminbr.readLine();
                                    if(temppass.equals(pass)){
                                        perso = true;
                                        iterator = it;
                                        break;
                                    }else{break;}
                                }
                                adminbr.readLine();
                            }else{
                                adminbr.readLine();
                                adminbr.readLine();
                                adminbr.readLine();
                                adminbr.readLine();
                                adminbr.readLine();
                            }
                        }else{break;}
                    }
                    adminbr.close();
                }
                
            }catch(IOException e) {
                System.out.print(e.getMessage());
            }
            
            TemelGUI.girisno.setText("");
            TemelGUI.girissifre.setText("");
            if(kul){
                Kul login = new Kullanici(iterator);
                kul = false;
            }else if(adm){
                Admin login = new Admin(iterator);
                adm = false;
            }else if(perso){
                Personel login = new Personel(iterator);
                perso = false;
            }else{
                JOptionPane.showMessageDialog(new JFrame(),"Bilgileri Eksik Veya Yanlış Girdiniz","UYARI",JOptionPane.WARNING_MESSAGE);
            }
        }});
        
    }
    
    static void mybounds(Component nesne, int width, int height, int x, int y){
        nesne.setSize(width, height);
        nesne.setLocation(((x/2)-(width/2)),y);
    }
    
    static void mybounds(Component nesne,int x, int y){
        nesne.setLocation(((x/2)-(nesne.getSize().width/2)),y);
    }
}

class ScrollablePanel extends JPanel implements Scrollable{
        public Dimension getPreferredScrollableViewportSize() {
            return super.getPreferredSize();
        }

        public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
            return 16;
        }

        public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
            return 16;
        }

        public boolean getScrollableTracksViewportWidth() {
            return true;
        }

        public boolean getScrollableTracksViewportHeight() {
            return false;
        }
    }

public class Nypproje {
    public static void main(String[] Args) {
        Arayuz test = new Arayuz();
    }
}