package com.jamme.dev.dyanmic.search.dyanmic.search.dto;

public record CustomerSearchRequest(
        String firstName,
        String lastName,
        String email,
        String gender,
        String ipAddress,
        String kycStatus
) {
}
