package com.postalservice.postal.controller;
import com.postalservice.postal.domain.*;
import com.postalservice.postal.repo.*;
import com.postalservice.postal.service.*;
import com.postalservice.postal.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
@RestController
@RequestMapping("/api")
public class ApiController {
  private final LetterRepository letters;
  private final ParcelRepository parcels;
  private final IdService ids;
  private final QueuePublisher queues;
  public ApiController(LetterRepository l, ParcelRepository p, IdService ids, QueuePublisher q){
    this.letters = l; this.parcels = p; this.ids = ids; this.queues = q;
  }
  @PostMapping("/letter/{country}/{name}")
  public ResponseEntity<Map<String,String>> createLetter(@PathVariable String country, @PathVariable String name){
    String id = ids.newId();
    Letter letter = new Letter();
    letter.setId(id); letter.setCountry(country.toUpperCase()); letter.setName(name);
    letters.save(letter);
    queues.sendLetter(id);
    return ResponseEntity.ok(Map.of("id", id, "status", letter.getStatus().name()));
  }
  @PostMapping("/package/{weight}/{name}")
  public ResponseEntity<Map<String,String>> createParcel(@PathVariable double weight, @PathVariable String name){
    String id = ids.newId();
    Parcel parcel = new Parcel();
    parcel.setId(id); parcel.setName(name); parcel.setWeightKg(weight);
    parcels.save(parcel);
    queues.sendParcel(id);
    return ResponseEntity.ok(Map.of("id", id, "status", parcel.getStatus().name()));
  }
  @GetMapping("/status")
  public StatusResponse status(){
    List<StatusItemDto> items = new ArrayList<>();
    letters.findAll().forEach(l -> items.add(StatusItemDto.fromLetter(l)));
    parcels.findAll().forEach(p -> items.add(StatusItemDto.fromParcel(p)));
    return new StatusResponse(items);
  }
}
