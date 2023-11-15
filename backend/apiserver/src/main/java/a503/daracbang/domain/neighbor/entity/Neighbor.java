package a503.daracbang.domain.neighbor.entity;

import java.time.LocalDateTime;

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

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Neighbor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(nullable = false)
	LocalDateTime requestedAt;

	@Column
	LocalDateTime acceptedAt;

	@Column(nullable = false)
	boolean isCon;

	@ManyToOne
	@JoinColumn(name = "requester_id")
	Member requester;

	@ManyToOne
	@JoinColumn(name = "accepter_id")
	Member accepter;

	@Builder
	public Neighbor(Member requester, Member accepter, boolean isRequest) {
		this.requester = requester;
		this.accepter = accepter;
		this.requestedAt = LocalDateTime.now();
		this.isCon = isRequest;
	}

	public LocalDateTime acceptNeighbor() {
		this.isCon = true;
		this.acceptedAt = LocalDateTime.now();

		return acceptedAt;
	}

	public void updateAcceptedAt(LocalDateTime acceptedAt) {
		this.acceptedAt = acceptedAt;
	}
}
