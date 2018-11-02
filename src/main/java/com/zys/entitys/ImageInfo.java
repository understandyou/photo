package com.zys.entitys;

public class ImageInfo extends ImageInfoKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column image_info.login_id
     *
     * @mbggenerated
     */
    private Integer loginId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_info
     *
     * @mbggenerated
     */
    public ImageInfo(Integer id, String imgUrl, Integer loginId) {
        super(id, imgUrl);
        this.loginId = loginId;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "loginId=" + loginId +
                '}';
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_info
     *
     * @mbggenerated
     */
    public ImageInfo() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column image_info.login_id
     *
     * @return the value of image_info.login_id
     * @mbggenerated
     */
    public Integer getLoginId() {
        return loginId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column image_info.login_id
     *
     * @param loginId the value for image_info.login_id
     * @mbggenerated
     */
    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }
}