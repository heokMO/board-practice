create trigger update_time_reply_trigger
    before update on reply
    for each row
begin
    set new.update_time = now();
end;