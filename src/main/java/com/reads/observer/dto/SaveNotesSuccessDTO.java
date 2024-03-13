package com.reads.observer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveNotesSuccessDTO {
    private String userName;
    private String message;
    private boolean status;
}
