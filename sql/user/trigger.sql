create trigger update_time_trigger
    before update on user
    for each row
    begin
        set new.update_time = now();
    end;