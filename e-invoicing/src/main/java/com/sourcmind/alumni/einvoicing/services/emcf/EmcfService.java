package com.sourcmind.alumni.einvoicing.services.emcf;

import com.sourcmind.alumni.einvoicing.payloads.responses.emcf.StatusResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "normalizeInvoice", url = "http://localhost:8083/dgiInvoice")
public interface EmcfService {
}
