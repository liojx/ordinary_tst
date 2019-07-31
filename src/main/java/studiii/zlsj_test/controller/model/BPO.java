package studiii.zlsj_test.controller.model;

public class BPO {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private Long fId;

    /**
     * 
     */
    private String val;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val == null ? null : val.trim();
    }
}