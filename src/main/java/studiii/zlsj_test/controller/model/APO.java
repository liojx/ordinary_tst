package studiii.zlsj_test.controller.model;

public class APO {
    /**
     * 
     */
    private Long id;

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

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val == null ? null : val.trim();
    }
}