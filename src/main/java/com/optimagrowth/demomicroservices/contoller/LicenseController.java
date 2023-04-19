package com.optimagrowth.demomicroservices.contoller;

import com.optimagrowth.demomicroservices.model.License;
import com.optimagrowth.demomicroservices.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/organization/{organisationId}/license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable("organisationId") String organisationId,
            @PathVariable("licenseId") String licenseId
    ) {
        var license = licenseService.getLicense(licenseId, organisationId);
        license.add(
                linkTo(methodOn(LicenseController.class).getLicense(organisationId, license.getLicenseId())).withSelfRel(),
                linkTo(methodOn(LicenseController.class).createLicense(organisationId, license)).withRel("createLicense"),
                linkTo(methodOn(LicenseController.class).updateLicense(organisationId, license)).withRel("updateLicense"),
                linkTo(methodOn(LicenseController.class).deleteLicense(organisationId, license.getLicenseId())).withRel("deleteLicense")
        );
        return ResponseEntity.ok(license);
    }

    @PutMapping
    public ResponseEntity<String> updateLicense(
            @PathVariable("organisationId") String organisationId,
            @RequestBody License request
    ) {
        var message = licenseService.updateLicense(request, organisationId);
        return ResponseEntity.ok(message);
    }

    @PostMapping
    public ResponseEntity<String> createLicense(
            @PathVariable("organisationId") String organisationId,
            @RequestBody License request
    ) {
        var message = licenseService.createLicense(request, organisationId);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable("organisationId") String organisationId,
            @PathVariable("licenseId") String licenseId
    ) {
        var message = licenseService.deleteLicense(licenseId, organisationId);
        return ResponseEntity.ok(message);
    }
}
