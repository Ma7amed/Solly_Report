package sample.model;


import java.lang.reflect.Field;

public class SollyTicket {

    private String ticket_id;
    private String ticket_title;
    private String ticket_confirm_time;
    private String ticket_operator;
    private String ticket_operator_group;
    private String ticket_originator;
    private String ticket_phase;
    private String ticket_business_status;
    private String ticket_operation_time;
    private String ticket_creation_time;
    private String ticket_description;


    public SollyTicket(String ticket_id, String ticket_title, String ticket_confirm_time, String ticket_operator, String ticket_operator_group, String ticket_originator, String ticket_phase, String ticket_business_status, String ticket_operation_time,String ticket_creation_time) {
        this.ticket_id = ticket_id;
        this.ticket_title = ticket_title;
        this.ticket_confirm_time = ticket_confirm_time;
        this.ticket_operator = ticket_operator;
        this.ticket_operator_group = ticket_operator_group;
        this.ticket_originator = ticket_originator;
        this.ticket_phase = ticket_phase;
        this.ticket_business_status = ticket_business_status;
        this.ticket_operation_time = ticket_operation_time;
        this.ticket_creation_time = ticket_creation_time;
    }


    public String getTicket_description() {
        return ticket_description;
    }

    public void setTicket_description(String ticket_description) {
        this.ticket_description = ticket_description;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getTicket_title() {
        return ticket_title;
    }

    public void setTicket_title(String ticket_title) {
        this.ticket_title = ticket_title;
    }

    public String getTicket_confirm_time() {
        return ticket_confirm_time;
    }

    public void setTicket_confirm_time(String ticket_confirm_time) {
        this.ticket_confirm_time = ticket_confirm_time;
    }

    public String getTicket_operator() {
        return ticket_operator;
    }

    public void setTicket_operator(String ticket_operator) {
        this.ticket_operator = ticket_operator;
    }

    public String getTicket_operator_group() {
        return ticket_operator_group;
    }

    public void setTicket_operator_group(String ticket_operator_group) {
        this.ticket_operator_group = ticket_operator_group;
    }

    public String getTicket_originator() {
        return ticket_originator;
    }

    public void setTicket_originator(String ticket_originator) {
        this.ticket_originator = ticket_originator;
    }

    public String getTicket_phase() {
        return ticket_phase;
    }

    public void setTicket_phase(String ticket_phase) {
        this.ticket_phase = ticket_phase;
    }

    public String getTicket_business_status() {
        return ticket_business_status;
    }

    public void setTicket_business_status(String ticket_business_status) {
        this.ticket_business_status = ticket_business_status;
    }

    public String getTicket_operation_time() {
        return ticket_operation_time;
    }

    public void setTicket_operation_time(String ticket_operation_time) {
        this.ticket_operation_time = ticket_operation_time;
    }

    public String getTicket_creation_time() {
        return ticket_creation_time;
    }

    public void setTicket_creation_time(String ticket_creation_time) {
        this.ticket_creation_time = ticket_creation_time;
    }

    public String getByName(String name) {

        Class<?> c = this.getClass();

        Field f = null;
        try {
            f = c.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f.setAccessible(true);

        try {
            String value = (String) f.get(this);
            return value;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String toString() {
       String msg="";
        msg += ticket_id + ", ";
        msg += ticket_title + ", ";
        msg += ticket_confirm_time + ", ";
        msg += ticket_operator + ", ";
        msg += ticket_operator_group + ", ";
        msg += ticket_originator + ", ";
        msg += ticket_phase + ", ";
        msg += ticket_business_status + ", ";
        msg += ticket_operation_time + ", ";
        msg += ticket_creation_time;
        return msg;

    }
}
