package example.dto;

public class AktivacioniMailDTO {
    private Long userId;
    private String userEmail;
    private String tipNotifikacije;
    private String ime;
    private String prezime;
    private String aktivacioniLink;
    private String aktivacioniKod;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTipNotifikacije() {
        return tipNotifikacije;
    }

    public void setTipNotifikacije(String tipNotifikacije) {
        this.tipNotifikacije = tipNotifikacije;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAktivacioniLink() {
        return aktivacioniLink;
    }

    public void setAktivacioniLink(String aktivacioniLink) {
        this.aktivacioniLink = aktivacioniLink;
    }

    public String getAktivacioniKod() {
        return aktivacioniKod;
    }

    public void setAktivacioniKod(String aktivacioniKod) {
        this.aktivacioniKod = aktivacioniKod;
    }
}
