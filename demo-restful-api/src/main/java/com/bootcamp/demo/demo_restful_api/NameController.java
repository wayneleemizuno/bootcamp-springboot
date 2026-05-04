package com.bootcamp.demo.demo_restful_api;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class NameController {
  @GetMapping(value = "/names")
  public List<Name> getNames() {
    return MemoryDatabase.names;
  }

  // ! CRUD -> create(POST) read(GET) update(PUT) delete(DELETE)

  // * Create new object
  // e.g. sign up, place order
  @PostMapping(value = "/name")
  public Name addName(@RequestBody Name name) {
    return MemoryDatabase.names.add(name) ? name : null;
  }

  @GetMapping(value = "/get_by_last")
  public List<Name> getByLastName(@RequestParam String lastName) {
    // skip null check
    return MemoryDatabase.names.stream().filter(n -> lastName.equals(n.getLastName()))
        .collect(Collectors.toList());
  }

  // * Delete
  // normally, we delete resources by id
  @DeleteMapping(value = "/name/{lastName}")
  public Boolean deleteByLastName(@PathVariable String lastName) {
    // ! don't use remove() -> it removes only the first match
    return MemoryDatabase.names.removeIf(n -> lastName.equals(n.getLastName()));
  }

  // * Update
  // e.g. update profile picture, update delivery address
  @PutMapping(value = "/name/{id}")
  public Name updateName(@PathVariable Long id, @RequestBody Name newName) {
    for (int i = 0; i < MemoryDatabase.names.size(); i++) {
      if (id.equals(MemoryDatabase.names.get(i).getId())) {
        newName.setId(id);
        MemoryDatabase.names.set(i, newName);
      }
    }
    return newName;
  }
}
