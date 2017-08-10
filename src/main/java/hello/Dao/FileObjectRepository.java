package hello.Dao;

import hello.Entity.FileObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileObjectRepository extends JpaRepository<FileObject, Long> {
}
