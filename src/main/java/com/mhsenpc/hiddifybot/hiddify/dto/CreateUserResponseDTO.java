package com.mhsenpc.hiddifybot.hiddify.dto;

import java.time.LocalDate;

public class CreateUserResponseDTO {
    private String added_by_uuid;
    private String comment;
    private Integer current_usage_GB;
    private String ed25519_private_key;
    private String ed25519_public_key;
    private Boolean enable;
    private Integer id;
    private Boolean is_active;
    private String lang;
    private String mode;
    private String name;
    private Integer package_days;
    private LocalDate start_date;
    private Integer telegram_id;
    private Integer usage_limit_GB;
    private String uuid;
    private String wg_pk;
    private String wg_psk;
    private String wg_pub;
    private Object detail;  // For errors
    private String message; // For errors

    public String getAdded_by_uuid() {
        return added_by_uuid;
    }

    public void setAdded_by_uuid(String added_by_uuid) {
        this.added_by_uuid = added_by_uuid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCurrent_usage_GB() {
        return current_usage_GB;
    }

    public void setCurrent_usage_GB(Integer current_usage_GB) {
        this.current_usage_GB = current_usage_GB;
    }

    public String getEd25519_private_key() {
        return ed25519_private_key;
    }

    public void setEd25519_private_key(String ed25519_private_key) {
        this.ed25519_private_key = ed25519_private_key;
    }

    public String getEd25519_public_key() {
        return ed25519_public_key;
    }

    public void setEd25519_public_key(String ed25519_public_key) {
        this.ed25519_public_key = ed25519_public_key;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPackage_days() {
        return package_days;
    }

    public void setPackage_days(Integer package_days) {
        this.package_days = package_days;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public Integer getTelegram_id() {
        return telegram_id;
    }

    public void setTelegram_id(Integer telegram_id) {
        this.telegram_id = telegram_id;
    }

    public Integer getUsage_limit_GB() {
        return usage_limit_GB;
    }

    public void setUsage_limit_GB(Integer usage_limit_GB) {
        this.usage_limit_GB = usage_limit_GB;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getWg_pk() {
        return wg_pk;
    }

    public void setWg_pk(String wg_pk) {
        this.wg_pk = wg_pk;
    }

    public String getWg_psk() {
        return wg_psk;
    }

    public void setWg_psk(String wg_psk) {
        this.wg_psk = wg_psk;
    }

    public String getWg_pub() {
        return wg_pub;
    }

    public void setWg_pub(String wg_pub) {
        this.wg_pub = wg_pub;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
