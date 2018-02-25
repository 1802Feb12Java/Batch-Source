set serveroutput on;

declare
e_id employee.employeeid%type;
e_lname employee.lastname%type;
e_tit employee.title%type;
cursor e_curs is
  select employeeid, lastname, title from employee;
begin
  open e_curs;
  loop 
  fetch e_curs into e_id, e_lname, e_tit;
  exit when e_curs%notfound;
  dbms_output.put_line(e_id || ' ' || e_lname || ' ' || e_tit);
  end loop;
  close e_curs;
end;
/

declare
p_id playlisttrack.playlistid%type;     --so you don't have to know the type
p_track playlisttrack.trackid%type;
cursor p_curs is
  select playlistid, count(trackid) from playlisttrack group by playlistid order by playlistid;
begin
  open p_curs;
  loop 
  fetch p_curs into p_id, p_track;
  exit when p_curs%notfound;
    if p_track = 1 then
      dbms_output.put_line('Playlist ' || p_id || ' has ' || p_track || ' song in it.');
    else 
      dbms_output.put_line('Playlist ' || p_id || ' has ' || p_track || ' songs in it.');
    end if;
  end loop;
  close p_curs;
end;
/