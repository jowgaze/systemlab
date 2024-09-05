package com.systemlab.domain.supervisor.dto;

import com.systemlab.domain.user.Role;
import com.systemlab.domain.user.StatusUser;

public record SupervisorResponseDTO(Long id, String username, String registration, String name, String course,
                String entryperiod, StatusUser status, Role role) {
}
