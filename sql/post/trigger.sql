create trigger update_time_post_trigger
    before update on post
    for each row
begin
    set new.update_time = now();
end;