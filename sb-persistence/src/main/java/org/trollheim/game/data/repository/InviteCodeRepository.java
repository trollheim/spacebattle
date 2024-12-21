package org.trollheim.game.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trollheim.game.data.models.InviteCode;

public interface InviteCodeRepository extends JpaRepository<InviteCode, Long> {

    InviteCode findByInviteCode(String inviteCode);
}
