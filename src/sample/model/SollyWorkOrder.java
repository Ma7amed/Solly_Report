package sample.model;

/**
 * Created by m80028770 on 4/22/2017.
 */
public class SollyWorkOrder {

    private String parent_ticket_id;
    private String ticket_originator;
    private String workorder_id;
    private String workorder_originator;
    private String workorder_title;
    private String workorder_operator;
    private String workorder_operator_group;
    private String workorder_phase;
    private String workorder_business_status;
    private String workorder_operation_time;
    private String ticket_creation_time;

    public SollyWorkOrder(String parent_ticket_id, String ticket_originator, String workorder_id, String workorder_originator, String workorder_title, String workorder_operator, String workorder_operator_group, String workorder_phase, String workorder_business_status, String workorder_operation_time, String ticket_creation_time) {
        this.parent_ticket_id = parent_ticket_id;
        this.ticket_originator = ticket_originator;
        this.workorder_id = workorder_id;
        this.workorder_originator = workorder_originator;
        this.workorder_title = workorder_title;
        this.workorder_operator = workorder_operator;
        this.workorder_operator_group = workorder_operator_group;
        this.workorder_phase = workorder_phase;
        this.workorder_business_status = workorder_business_status;
        this.workorder_operation_time = workorder_operation_time;
        this.ticket_creation_time = ticket_creation_time;
    }

    public String getParent_ticket_id() {
        return parent_ticket_id;
    }

    public void setParent_ticket_id(String parent_ticket_id) {
        this.parent_ticket_id = parent_ticket_id;
    }

    public String getTicket_originator() {
        return ticket_originator;
    }

    public void setTicket_originator(String ticket_originator) {
        this.ticket_originator = ticket_originator;
    }

    public String getWorkorder_id() {
        return workorder_id;
    }

    public void setWorkorder_id(String workorder_id) {
        this.workorder_id = workorder_id;
    }

    public String getWorkorder_originator() {
        return workorder_originator;
    }

    public void setWorkorder_originator(String workorder_originator) {
        this.workorder_originator = workorder_originator;
    }

    public String getWorkorder_title() {
        return workorder_title;
    }

    public void setWorkorder_title(String workorder_title) {
        this.workorder_title = workorder_title;
    }

    public String getWorkorder_operator() {
        return workorder_operator;
    }

    public void setWorkorder_operator(String workorder_operator) {
        this.workorder_operator = workorder_operator;
    }

    public String getWorkorder_operator_group() {
        return workorder_operator_group;
    }

    public void setWorkorder_operator_group(String workorder_operator_group) {
        this.workorder_operator_group = workorder_operator_group;
    }

    public String getWorkorder_phase() {
        return workorder_phase;
    }

    public void setWorkorder_phase(String workorder_phase) {
        this.workorder_phase = workorder_phase;
    }

    public String getWorkorder_business_status() {
        return workorder_business_status;
    }

    public void setWorkorder_business_status(String workorder_business_status) {
        this.workorder_business_status = workorder_business_status;
    }

    public String getWorkorder_operation_time() {
        return workorder_operation_time;
    }

    public void setWorkorder_operation_time(String workorder_operation_time) {
        this.workorder_operation_time = workorder_operation_time;
    }

    public String getTicket_creation_time() {
        return ticket_creation_time;
    }

    public void setTicket_creation_time(String ticket_creation_time) {
        this.ticket_creation_time = ticket_creation_time;
    }

    @Override
    public String toString() {
        String msg="";
        msg += parent_ticket_id + ", ";
        msg += ticket_originator + ", ";
        msg += workorder_id + ", ";
        msg += workorder_originator + ", ";
        msg += workorder_title + ", ";
        msg += workorder_operator + ", ";
        msg += workorder_operator_group + ", ";
        msg += workorder_phase + ", ";
        msg += workorder_business_status + ", ";
        msg += workorder_operation_time + ", ";
        msg += ticket_creation_time;
        return msg;

    }
}
