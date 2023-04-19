package com.optimagrowth.demomicroservices.service;

import com.optimagrowth.demomicroservices.model.License;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class LicenseService {

    public License getLicense(String licenseId, String organisationId) {
        var license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganisationId(organisationId);
        license.setDescription("Software product");
        license.setProductName("Ostock");
        license.setLicenseType("full");
        return license;
    }

    public String createLicense(License license, String organizationId) {
        String responseMessage = null;
        if (Objects.nonNull(license)) {
            license.setOrganisationId(organizationId);
            responseMessage = String.format("This is the post and the object is: %s", license);
        }
        return responseMessage;
    }

    public String updateLicense(License license, String organizationId){
        String responseMessage = null;
        if (Objects.nonNull(license)) {
            license.setOrganisationId(organizationId);
            responseMessage = String.format("This is the put and the object is: %s", license);

        }
        return responseMessage;
    }

    public String deleteLicense(String licenseId, String organizationId){
        return String.format("Deleting license with id %s for the organization %s",licenseId, organizationId);
    }
}
