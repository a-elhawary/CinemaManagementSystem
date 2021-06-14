package views.sharedcomponents;

import helper.Months;
import views.AppColors;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class DatePicker extends JPanel {
    JComboBox chooseMonthCombo;
    JComboBox chooseDayCombo;
    JComboBox chooseYearCombo;

    public DatePicker(String labelText){
        String[] years = initComponents();
        chooseMonthCombo = new JComboBox(Months.names);
        chooseDayCombo = new JComboBox(Months.getDaysArray(Months.names[0]));
        chooseYearCombo = new JComboBox(years);
        chooseMonthCombo.addActionListener(new MonthActionlistener(chooseMonthCombo, chooseDayCombo, this));
        addComponents(labelText);
    }

    public DatePicker(String labelText, Date startDate, Date endDate){
        String[] years = initComponents();
        chooseMonthCombo = new JComboBox(Months.getMonthNames(startDate.getMonth(), endDate.getMonth()));
        System.out.println(startDate.getDate());
        System.out.println(endDate.getDate());
        String[] temp = Months.getDaysArray(Months.names[startDate.getMonth()]);
        chooseDayCombo = new JComboBox(Months.generateDaysUntil(startDate.getDate(),Integer.parseInt(temp[temp.length - 1])));
        chooseYearCombo = new JComboBox(years);
        chooseMonthCombo.addActionListener(new MonthActionlistener(chooseMonthCombo, chooseDayCombo, this,startDate, endDate));
        addComponents(labelText);
    }
    private String[] initComponents(){
        int year = Months.getCurrentYear();
        String[] years = {String.valueOf(year), String.valueOf(year + 1)};
        this.setBackground(null);
        return years;
    }

    private void addComponents(String labelText){
        JLabel startDateLabel = new JLabel(labelText);
        startDateLabel.setForeground(AppColors.darkGrey);
        this.add(startDateLabel);
        this.add(chooseMonthCombo);
        this.add(chooseDayCombo);
        this.add(chooseYearCombo);
    }


    public Date getDate(){
        return new Date(Integer.parseInt((String)chooseYearCombo.getSelectedItem()) - 1900, chooseMonthCombo.getSelectedIndex(), Integer.parseInt((String)chooseDayCombo.getSelectedItem()));
    }

    public void reset(){
        this.chooseDayCombo.setSelectedIndex(0);
        this.chooseYearCombo.setSelectedIndex(0);
        this.chooseMonthCombo.setSelectedIndex(0);
    }
}

class MonthActionlistener implements ActionListener {

    JComboBox monthCombo;
    JComboBox dayCombo;
    JPanel container;
    Date endDate;
    Date startDate;

    public MonthActionlistener(JComboBox monthCombo, JComboBox dayCombo, JPanel container){
        this.monthCombo = monthCombo;
        this.dayCombo = dayCombo;
        this.container = container;
    }

    public MonthActionlistener(JComboBox monthCombo, JComboBox dayCombo, JPanel container, Date startDate, Date endDate ){
        this.monthCombo = monthCombo;
        this.dayCombo = dayCombo;
        this.container = container;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        dayCombo.removeAllItems();
        String currentMonthName = (String)(monthCombo.getSelectedItem());
        if(endDate != null && currentMonthName.equals(Months.names[endDate.getMonth()])){
            for(String day : Months.generateDaysUntil(1, endDate.getDate())){
                dayCombo.addItem(day);
            }
        }else if (endDate != null && currentMonthName.equals(Months.names[startDate.getMonth()])){
            String[] days = Months.getDaysArray(currentMonthName);
            for(String day : Months.generateDaysUntil(startDate.getDate(), Integer.parseInt(days[days.length - 1]))){
                dayCombo.addItem(day);
            }
        }else{
            for(String day : Months.getDaysArray(Months.names[monthCombo.getSelectedIndex()])) {
                dayCombo.addItem(day);
            }
        }
        container.revalidate();
        container.repaint();
    }
}
