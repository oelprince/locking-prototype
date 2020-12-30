package com.oelprince.locking.prototype.rest;

import com.oelprince.locking.prototype.entities.ClBill;
import com.oelprince.locking.prototype.entities.ClBillDetl;
import com.oelprince.locking.prototype.repository.ClBillDetlRepository;
import com.oelprince.locking.prototype.repository.ClBillRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.LockModeType;
import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Slf4j
@RestController
public class BillResourceController {

    @Autowired
    private ClBillRepository clBillRepository;

    @Autowired
    private ClBillDetlRepository clBillDetlRepository;

    private SecureRandom secureRandom = new SecureRandom();


    @Setter
    @Getter
    @Builder
    public static class BillResponse {
        private ClBill clBill;


    }

    @Transactional
    @PostMapping(value = "/save-bill", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BillResponse> saveBill(@RequestBody ClBill clBill) {
        long randomLong = secureRandom.nextInt();

        clBill.setBillNbr(String.valueOf(Math.abs(randomLong)));
        clBill.setCrteTs(Timestamp.from(Instant.now()));
        clBill.setUpdTs(Timestamp.from(Instant.now()));
        ClBill clBillSaved = clBillRepository.save(clBill);
        BillResponse billResponse = BillResponse.builder().clBill(clBillSaved).build();

        return ResponseEntity.ok(billResponse);
    }


    @Transactional
    @PutMapping(value = "/delete-bill-detail/{billNbr}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BillResponse> deleteBill(@PathVariable("billNbr") String billNbr) {
        Optional<ClBillDetl> clBillDetlOptionl = clBillDetlRepository.findById(billNbr);

        if(!clBillDetlOptionl.isPresent()) {
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        }

        clBillDetlRepository.delete(clBillDetlOptionl.get());

        Optional<ClBill> billOptional = clBillRepository.findById(clBillDetlOptionl.get().getClBillNbr());

        ClBill clBill = billOptional.get();

        BigDecimal billTotalAmd = clBill.getBillTotlAmt().subtract(clBillDetlOptionl.get().getEntryAmt());

        clBill.setBillTotlAmt(billTotalAmd);

        clBillRepository.save(clBill);

        return new ResponseEntity(clBillDetlOptionl.get(), HttpStatus.ACCEPTED);
    }




    @Transactional
    @PostMapping(value = "/save-bill-detail", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BillResponse> saveBillDetail(@RequestParam String filerCd, @RequestBody ClBillDetl clBillDetl) {
        long randomLong = secureRandom.nextInt();

        log.info("##### entryNbr {} entryAmt {}",clBillDetl.getEntryNbr(), clBillDetl.getEntryAmt());

        ClBill clBill = clBillRepository.findClBillBy(filerCd);

        if(clBill == null) {
            clBill = new ClBill();
            clBill.setBillStusCd("CREATE");
            clBill.setFilerCd(filerCd);
            clBill.setBillNbr(String.valueOf(Math.abs(randomLong)));
            clBill.setBillTotlAmt(clBillDetl.getEntryAmt());
            clBill.setCrteTs(Timestamp.from(Instant.now()));
            clBill.setUpdTs(Timestamp.from(Instant.now()));
            clBill.setCrteById("SYS");
            clBill.setUpdById("SYS");
        } else {
            BigDecimal totalAmt = clBill.getBillTotlAmt().add(clBillDetl.getEntryAmt());
            clBill.setBillTotlAmt(totalAmt);
        }
        ClBill clBillSaved = clBillRepository.save(clBill);

        long randomLong1 = secureRandom.nextInt();

        clBillDetl.setEntryBillNbr(String.valueOf(Math.abs(randomLong1)));
        clBillDetl.setClBillNbr(clBillSaved.getBillNbr());
        clBillDetl.setCrteTs(Timestamp.from(Instant.now()));
        clBillDetl.setUpdTs(Timestamp.from(Instant.now()));
        clBillDetl.setCrteById("SYS");
        clBillDetl.setUpdById("SYS");

        ClBillDetl clBillDetlSaved = clBillDetlRepository.save(clBillDetl);

        BillResponse billResponse = BillResponse.builder().clBill(clBillSaved).build();

        return ResponseEntity.ok(billResponse);
    }

}
