package a503.daracbang.domain.bgm.entity;

import a503.daracbang.domain.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.MalformedURLException;
import java.net.URL;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Bgm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String bgmName;

    @Column(nullable = false)
    private String videoId;

    public boolean isOwner(long memberId) {
        if(this.member.getId() != memberId) {
            return false;
        }
        return true;
    }

    public Bgm(Member member, String bgmName, String url) {
        this.member = member;
        this.bgmName = bgmName;
        this.videoId = parseVideoId(url);
    }

    private String parseVideoId(String url){
        URL aURL = null;
        try {
            aURL = new URL(url);
        } catch (MalformedURLException e) {
            return "";
        }
        String query = aURL.getQuery();
        String[] params = query.split("&");

        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            if ("v".equals(name)) {
                return value;
            }
        }
        return "";
    }
}
