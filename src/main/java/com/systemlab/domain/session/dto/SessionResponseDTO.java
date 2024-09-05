package com.systemlab.domain.session.dto;

import com.systemlab.domain.session.ShiftSession;

public record SessionResponseDTO(Long id, Boolean status, ShiftSession shift, Long laboratoryId, String laboratory, Long supervisorId, String supervisor) {

}
